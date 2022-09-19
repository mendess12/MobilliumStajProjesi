package com.yusufmendes.basicnoteapp.ui.notedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.yusufmendes.basicnoteapp.R
import com.yusufmendes.basicnoteapp.databinding.FragmentNoteDetailBinding
import com.yusufmendes.basicnoteapp.di.RetrofitClient
import com.yusufmendes.basicnoteapp.model.note.NoteDataList
import com.yusufmendes.basicnoteapp.viewmodel.NoteDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private lateinit var viewModel: NoteDetailViewModel
    private val args: NoteDetailFragmentArgs by navArgs()
    val retrofitClient = RetrofitClient()
    var note: NoteDataList? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNoteDetailBinding.bind(view)
        viewModel = ViewModelProviders.of(activity!!).get(NoteDetailViewModel::class.java)

        note = args.note
        if (note == null) {
            // new Note
            binding.noteSaveButton.setOnClickListener {
                viewModel.title = binding.noteDetailTitleEditText.text.toString()
                viewModel.note = binding.noteDetailEditText.text.toString()

                if (viewModel.title.isEmpty()) {
                    binding.noteDetailTitleEditText.error = "This area can not be empty"
                    binding.noteDetailTitleEditText.requestFocus()
                    return@setOnClickListener
                }
                if (viewModel.note.isEmpty()) {
                    binding.noteDetailEditText.error = "This area can not be empty"
                    binding.noteDetailEditText.requestFocus()
                    return@setOnClickListener
                }
                addNoteObserverLiveData()
            }
        } else {
            binding.noteDetailTitleEditText.setText(note!!.noteTitle)
            binding.noteDetailEditText.setText(note!!.noteDetail)

            // Note editing
            binding.noteSaveButton.setOnClickListener {
                viewModel.title = binding.noteDetailTitleEditText.text.toString()
                viewModel.note = binding.noteDetailEditText.text.toString()
                viewModel.noteId = note!!.id

                viewModel.updateData.observe(viewLifecycleOwner) {
                    if (it != null) {
                        val action =
                            NoteDetailFragmentDirections.actionNoteDetailFragmentToNotesFragment()
                        Navigation.findNavController(requireView()).navigate(action)
                    }
                }
                viewModel.updateNote()
            }
        }

        //back icon'una tiklaninca yapilacak islemler
        binding.noteDetailBackButton.setOnClickListener {
            val action = NoteDetailFragmentDirections.actionNoteDetailFragmentToNotesFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun addNoteObserverLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            if (it != null) {
                val action =
                    NoteDetailFragmentDirections.actionNoteDetailFragmentToNotesFragment()
                Navigation.findNavController(requireView()).navigate(action)
            }
        }
        viewModel.addNote()
    }
}
