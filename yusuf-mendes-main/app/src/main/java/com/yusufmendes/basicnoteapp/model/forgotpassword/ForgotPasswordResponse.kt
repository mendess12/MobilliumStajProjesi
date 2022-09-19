package com.yusufmendes.basicnoteapp.model.forgotpassword

import com.google.gson.annotations.SerializedName

data class ForgotPasswordResponse(

    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val data: String? = null,
    @SerializedName("message")
    val message: String,
)
