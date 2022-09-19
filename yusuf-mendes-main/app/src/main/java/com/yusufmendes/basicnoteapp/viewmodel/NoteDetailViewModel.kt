package com.yusufmendes.basicnoteapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufmendes.basicnoteapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var title: String = ""
    var note: String = ""
    var noteId: Int = 1
    val liveData = MutableLiveData<String>()
    val updateData = MutableLiveData<String>()

    fun addNote() = repository.addNote(title, note, liveData)

    fun updateNote() = repository.updateNote(title, note, noteId, updateData)
}
