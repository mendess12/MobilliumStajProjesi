package com.yusufmendes.basicnoteapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufmendes.basicnoteapp.model.note.NoteDataList
import com.yusufmendes.basicnoteapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private var _notesLiveData: MutableLiveData<List<NoteDataList>> = MutableLiveData()
    val notesLiveData: LiveData<List<NoteDataList>> = _notesLiveData
    var deleteData: MutableLiveData<Boolean> = MutableLiveData()

    fun loadListOfData() {
        repository.getNote(_notesLiveData)
    }

    fun delete(position: Int) {
        if (_notesLiveData.value != null) {
            val item = _notesLiveData.value!![position]
            repository.delete(item.id, deleteData)
        }
    }
}
