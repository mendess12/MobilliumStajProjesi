package com.yusufmendes.basicnoteapp.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("code")
    val code: String = "",
    @SerializedName("data")
    val loginData: LoginData?,
    @SerializedName("message")
    val message: String = "",
)
