package com.yusufmendes.basicnoteapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufmendes.basicnoteapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    var password: String = ""
    var newPassword: String = ""
    var retypeNewPassword: String = ""
    val liveData = MutableLiveData<String>()

    fun changePassword() =
        repository.changePassword(password, newPassword, retypeNewPassword, liveData)
}
