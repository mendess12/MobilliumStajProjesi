package com.yusufmendes.basicnoteapp.model.update

import com.google.gson.annotations.SerializedName

data class ChangePassword(

    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String,
)
