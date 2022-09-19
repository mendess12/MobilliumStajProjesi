package com.yusufmendes.basicnoteapp.model.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String,
)
