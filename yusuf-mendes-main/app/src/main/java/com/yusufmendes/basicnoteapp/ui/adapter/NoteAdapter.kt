package com.yusufmendes.basicnoteapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.yusufmendes.basicnoteapp.R
import com.yusufmendes.basicnoteapp.model.note.NoteDataList
import kotlinx.android.synthetic.main.item_notes.view.*
import java.util.*

class NoteAdapter(private val listener: Listener) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(), Filterable {

    var searchableList: MutableList<NoteDataList> = arrayListOf()
    var originalSearchableList: MutableList<NoteDataList> = arrayListOf()

    interface Listener {
        fun onItemClick(noteDataList: NoteDataList)
    }

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(noteList: NoteDataList, listener: Listener) {
            itemView.setOnClickListener {
                listener.onItemClick(noteList)
            }
            itemView.noteTitle.text = noteList.noteTitle
            itemView.noteDetail.text = noteList.noteDetail
        }
    }

    //olusturulan item_notes ile adapter baglama islemleri
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_notes, parent, false)
        return NoteViewHolder(view)
    }

    //item_notes daki textlere erisme
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(searchableList[position], listener)

        //recyclerView icindeki iteme tıklanınca yapılacak islemler
        holder.itemView.setOnClickListener {
            listener.onItemClick(searchableList[position])
            notifyDataSetChanged()
        }
    }

    //kaç tane row olusturulacak
    override fun getItemCount(): Int {
        return searchableList.size
    }

    //filtreleme işlemleri
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                searchableList = results.values as MutableList<NoteDataList>
                notifyDataSetChanged()
            }

            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filteredResults: List<NoteDataList?>?
                if (constraint.length == 0) {
                    filteredResults = originalSearchableList
                } else {
                    filteredResults =
                        getFilteredResults(constraint.toString().lowercase(Locale.getDefault()))
                }
                val results = FilterResults()
                results.values = filteredResults
                return results
            }
        }
    }

    //filter sonuçları
    protected fun getFilteredResults(constraint: String?): List<NoteDataList> {
        val results: MutableList<NoteDataList> = ArrayList()
        for (item in originalSearchableList) {
            if (item.noteTitle.toLowerCase().contains(constraint!!)) {
                results.add(item)
            }
        }
        return results
    }

    //filter'a göre listeleme
    fun setList(data: List<NoteDataList>) {
        searchableList.clear()
        searchableList.addAll(data)
        originalSearchableList.clear()
        originalSearchableList.addAll(data)
        notifyDataSetChanged()
    }
}
