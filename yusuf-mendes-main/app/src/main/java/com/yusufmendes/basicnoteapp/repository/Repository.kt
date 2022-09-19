package com.yusufmendes.basicnoteapp.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.yusufmendes.basicnoteapp.di.RetrofitClient
import com.yusufmendes.basicnoteapp.model.NoteDetail.AddNote
import com.yusufmendes.basicnoteapp.model.forgotpassword.ForgotPasswordResponse
import com.yusufmendes.basicnoteapp.model.login.LoginResponse
import com.yusufmendes.basicnoteapp.model.note.Note
import com.yusufmendes.basicnoteapp.model.note.NoteDataList
import com.yusufmendes.basicnoteapp.model.register.RegisterResponse
import com.yusufmendes.basicnoteapp.model.update.ChangePassword
import com.yusufmendes.basicnoteapp.model.update.UpdateMe
import com.yusufmendes.basicnoteapp.model.update.UpdateNoteData
import com.yusufmendes.basicnoteapp.util.storage.SharedPrefManager
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    val retrofitClient: RetrofitClient,
    @ApplicationContext private val context: Context,
) {
    val accessToken = "Bearer " + SharedPrefManager.getInstance(context).data.accessToken

    //getNote
    fun getNote(_notesLiveData: MutableLiveData<List<NoteDataList>>) {
        retrofitClient.retrofitInstance().getNote(1, accessToken)
            .enqueue(object : Callback<Note> {
                override fun onResponse(call: Call<Note>, response: Response<Note>) {
                    if (response.isSuccessful) {
                        _notesLiveData.value = response.body()?.dataOne?.data
                    } else {
                        _notesLiveData.value = null
                    }
                }

                override fun onFailure(call: Call<Note>, t: Throwable) {
                    _notesLiveData.value = null
                }
            })
    }

    //login
    fun login(
        email: String,
        password: String,
        liveData: MutableLiveData<String>,
    ) {
        retrofitClient.retrofitInstance().userLogin(email, password)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>,
                ) {
                    if (response.isSuccessful) {
                        SharedPrefManager.getInstance(context)
                            .saveUser(response.body()?.loginData!!)
                        liveData.value = response.body()?.toString()
                    } else {
                        Log.d("TAG", "onResponse: fail")
                        liveData.value = null
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    liveData.value = null
                    Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
    }

    //register
    fun register(
        fullName: String,
        email: String,
        password: String,
        liveData: MutableLiveData<String>,
    ) {
        retrofitClient.retrofitInstance().createUser(fullName, email, password)
            .enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>,
                ) {
                    if (response.isSuccessful) {
                        liveData.value = response.body()?.toString()
                    } else {
                        liveData.value = null
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    liveData.value = null
                    Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
    }

    //forgotPassword
    fun forgotPassword(email: String, liveData: MutableLiveData<String>) {
        retrofitClient.retrofitInstance().changePassword(email)
            .enqueue(object : Callback<ForgotPasswordResponse> {
                override fun onResponse(
                    call: Call<ForgotPasswordResponse>,
                    response: Response<ForgotPasswordResponse>,
                ) {
                    if (response.isSuccessful) {
                        liveData.postValue(response.body()?.toString())
                    } else {
                        liveData.value = null
                    }
                }

                override fun onFailure(call: Call<ForgotPasswordResponse>, t: Throwable) {
                    liveData.value = null
                    Toast.makeText(context, "kullanıcı bulunamadı.", Toast.LENGTH_LONG).show()
                }
            })
    }

    //delete
    fun delete(noteId: Int, liveData: MutableLiveData<Boolean>) {
        retrofitClient.retrofitInstance().deleteNote(noteId, accessToken)
            .enqueue(object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    if (response.isSuccessful) {
                        Log.d("TAG", "onResponse: success")
                        liveData.postValue(true)
                    } else {
                        liveData.postValue(false)
                        Log.d("TAG", "onResponse: fail")
                    }
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    liveData.postValue(false)
                    Log.d("TAG", "onFailure: fail")
                }
            })
    }

    //updateMe
    fun updateMe(fullName: String, email: String, liveData: MutableLiveData<String>) {
        retrofitClient.retrofitInstance().updateMe(fullName, email, accessToken)
            .enqueue(object : Callback<UpdateMe> {
                override fun onResponse(call: Call<UpdateMe>, response: Response<UpdateMe>) {
                    if (response.isSuccessful) {
                        Log.d("TAG", "onResponse: success")
                        liveData.value = response.body().toString()
                    } else {
                        Log.d("TAG", "onResponse: fail")
                        liveData.value = null
                    }
                }

                override fun onFailure(call: Call<UpdateMe>, t: Throwable) {
                    Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                    liveData.value = null
                }
            })
    }

    //add note
    fun addNote(title: String, note: String, liveData: MutableLiveData<String>) {
        retrofitClient.retrofitInstance().addNote(title, note, accessToken)
            .enqueue(object : Callback<AddNote> {
                override fun onResponse(call: Call<AddNote>, response: Response<AddNote>) {
                    if (response.isSuccessful) {
                        liveData.value = response.body().toString()
                    } else {
                        liveData.value = null
                    }
                }

                override fun onFailure(call: Call<AddNote>, t: Throwable) {
                    Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                    liveData.value = null
                }
            })
    }

    //updateNote
    fun updateNote(title: String, note: String, noteId: Int, updateData: MutableLiveData<String>) {
        retrofitClient.retrofitInstance().updateNote(title, note, noteId, accessToken)
            .enqueue(object : Callback<UpdateNoteData> {
                override fun onResponse(
                    call: Call<UpdateNoteData>,
                    response: Response<UpdateNoteData>,
                ) {
                    if (response.isSuccessful) {
                        updateData.value = response.body().toString()
                    } else {
                        updateData.value = null
                    }
                }

                override fun onFailure(call: Call<UpdateNoteData>, t: Throwable) {
                    Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                    updateData.value = null
                }
            })
    }

    //changePassword
    fun changePassword(
        password: String,
        newPassword: String,
        retypeNewPassword: String,
        liveData: MutableLiveData<String>,
    ) {
        retrofitClient.retrofitInstance()
            .updatePassword(password, newPassword, retypeNewPassword, accessToken)
            .enqueue(object : Callback<ChangePassword> {
                override fun onResponse(
                    call: Call<ChangePassword>,
                    response: Response<ChangePassword>,
                ) {
                    if (response.isSuccessful) {
                        liveData.value = response.body().toString()
                    } else {
                        liveData.value = null
                    }
                }

                override fun onFailure(call: Call<ChangePassword>, t: Throwable) {
                    liveData.value = null
                    Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
    }
}

