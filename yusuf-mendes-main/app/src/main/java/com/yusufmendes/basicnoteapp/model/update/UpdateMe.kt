package com.yusufmendes.basicnoteapp.model.update

import com.google.gson.annotations.SerializedName

data class UpdateMe(

    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String,
)
