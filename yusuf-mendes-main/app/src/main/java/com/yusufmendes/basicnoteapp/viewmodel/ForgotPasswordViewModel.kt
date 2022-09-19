package com.yusufmendes.basicnoteapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufmendes.basicnoteapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    var email: String = ""
    val liveData = MutableLiveData<String>()

    fun forgotPasswordData() = repository.forgotPassword(email, liveData)
}
