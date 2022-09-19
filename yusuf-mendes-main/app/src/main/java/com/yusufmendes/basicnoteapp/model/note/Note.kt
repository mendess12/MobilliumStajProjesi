package com.yusufmendes.basicnoteapp.model.note

import com.google.gson.annotations.SerializedName

data class Note(

    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val dataOne: NoteData,
    @SerializedName("message")
    val message: String,
)

