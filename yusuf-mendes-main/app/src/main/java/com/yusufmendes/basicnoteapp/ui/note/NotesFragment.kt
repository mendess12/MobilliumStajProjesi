package com.yusufmendes.basicnoteapp.ui.note

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yusufmendes.basicnoteapp.R
import com.yusufmendes.basicnoteapp.databinding.FragmentNotesBinding
import com.yusufmendes.basicnoteapp.model.note.NoteDataList
import com.yusufmendes.basicnoteapp.ui.adapter.NoteAdapter
import com.yusufmendes.basicnoteapp.util.listener.MyButtonClickListener
import com.yusufmendes.basicnoteapp.util.swipe.MyButton
import com.yusufmendes.basicnoteapp.util.swipe.MySwipeHelper
import com.yusufmendes.basicnoteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_notes.*

@AndroidEntryPoint
class NotesFragment : Fragment(), NoteAdapter.Listener {

    private lateinit var binding: FragmentNotesBinding
    private lateinit var viewModel: NoteViewModel
    private var recyclerNoteAdapter: NoteAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotesBinding.bind(view)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        viewModel = ViewModelProviders.of(requireActivity()).get(NoteViewModel::class.java)

        observeLiveData()
        search()
        swipe()
        refresData()

        //cancel buttonuna tıklanınca yapılacak işlemler
        binding.cancel.setOnClickListener {
            binding.noteSearch.setText("")
            binding.cancel.visibility = View.GONE
            binding.userImageView.visibility = View.VISIBLE
        }
        //user image view'a tiklaninca yapilacak islemler
        binding.userImageView.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToProfileFragment()
            Navigation.findNavController(it).navigate(action)
        }
        //add note'a tiklaninca yapilacak islemler
        binding.addNoteButton.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToNoteDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    //recyclerItemClick islemleri
    override fun onItemClick(noteDataList: NoteDataList) {
        val action = NotesFragmentDirections.actionNotesFragmentToNoteDetailFragment(noteDataList)
        findNavController().navigate(action)
    }

    //swipe controller islemleri
    //RecyclerView'a helper eklendi
    fun swipe() {
        //geri arama iletme işlemleri
        val swiper = object : MySwipeHelper(activity!!, recyclerView, 300) {
            override fun instantiateMyButton(
                viewHolder: RecyclerView.ViewHolder,
                buffer: MutableList<MyButton>,
            ) {
                //add buffer
                buffer.add(
                    MyButton(activity!!,
                        "Delete",
                        30,
                        R.drawable.trash_icon,
                        Color.parseColor("#dd2c00"),
                        object : MyButtonClickListener {
                            override fun onClick(position: Int) {

                                val alertDialog = AlertDialog.Builder(activity)
                                alertDialog.setTitle("Delete Note")
                                alertDialog.setMessage("Are you sure you want to delete this note.")
                                alertDialog.setCancelable(false)
                                alertDialog.setPositiveButton("DELETE",
                                    object : DialogInterface.OnClickListener {
                                        override fun onClick(p0: DialogInterface?, p1: Int) {
                                            viewModel.delete(position)
                                            viewModel.loadListOfData()
                                            Toast.makeText(activity, "Deleted", Toast.LENGTH_LONG)
                                                .show()
                                        }
                                    })
                                alertDialog.setNegativeButton("CANCEL",
                                    object : DialogInterface.OnClickListener {
                                        override fun onClick(p0: DialogInterface?, p1: Int) {
                                            Toast.makeText(
                                                activity,
                                                "Cancelled.",
                                                Toast.LENGTH_LONG
                                            )
                                                .show()
                                        }
                                    })
                                alertDialog.show()
                            }
                        })
                )
                buffer.add(
                    MyButton(activity!!,
                        "Edit",
                        30,
                        R.drawable.edit_icon,
                        Color.parseColor("#FFD164"),
                        object : MyButtonClickListener {
                            override fun onClick(position: Int) {
                                val item =
                                    recyclerNoteAdapter!!.searchableList[position]
                                val action =
                                    NotesFragmentDirections.actionNotesFragmentToNoteDetailFragment(
                                        item
                                    )
                                findNavController().navigate(action)
                                Toast.makeText(
                                    activity,
                                    "clicked update" + position,
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                        })
                )
            }
        }
    }

    fun refresData() {
        binding.noteScreenSwipeRefreshLayout.setOnRefreshListener {
            viewModel.loadListOfData()
            binding.noteScreenSwipeRefreshLayout.isRefreshing = false
        }
    }

    //search editText tıklanınca yapılacak işlemler
    fun search() {
        binding.noteSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.userImageView.visibility = View.VISIBLE
                binding.cancel.visibility = View.GONE
            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                recyclerNoteAdapter?.filter?.filter(charSequence.toString())

            }

            override fun afterTextChanged(editable: Editable?) {
                binding.userImageView.visibility = View.GONE
                binding.cancel.visibility = View.VISIBLE
            }
        })
    }

    //verileri gozlemleme fonksiyonu
    fun observeLiveData() {
        viewModel.notesLiveData.observe(viewLifecycleOwner, object : Observer<List<NoteDataList>> {
            override fun onChanged(t: List<NoteDataList>?) {
                if (t != null) {
                    recyclerNoteAdapter = NoteAdapter(this@NotesFragment)
                    recyclerNoteAdapter!!.setList(t)
                    binding.recyclerView.adapter = recyclerNoteAdapter
                } else
                    Toast.makeText(
                        activity,
                        "note model doldurulamadı",
                        Toast.LENGTH_LONG
                    ).show()
            }
        })
        viewModel.loadListOfData()

        viewModel.deleteData.observe(viewLifecycleOwner) {
            // Snackbar.make(binding.root, "Deleted: $it", Snackbar.LENGTH_SHORT).show()
        }
    }
}
