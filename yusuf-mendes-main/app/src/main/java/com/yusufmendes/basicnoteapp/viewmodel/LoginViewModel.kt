package com.yusufmendes.basicnoteapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufmendes.basicnoteapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var email: String = ""
    var password: String = ""

    fun loginData() = repository.login(email, password, theData)

    val theData = MutableLiveData<String>()
}
