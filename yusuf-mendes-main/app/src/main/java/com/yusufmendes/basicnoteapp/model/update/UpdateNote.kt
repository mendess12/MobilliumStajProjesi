package com.yusufmendes.basicnoteapp.model.update

import com.google.gson.annotations.SerializedName

data class UpdateNote(

    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val data: UpdateNoteData,
    @SerializedName("message")
    val message: String,
)

