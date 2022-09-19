package com.yusufmendes.basicnoteapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufmendes.basicnoteapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var fullName = ""
    var email: String = ""
    var password: String = ""
    val liveData = MutableLiveData<String>()

    fun registerData() = repository.register(fullName, email, password, liveData)
}
