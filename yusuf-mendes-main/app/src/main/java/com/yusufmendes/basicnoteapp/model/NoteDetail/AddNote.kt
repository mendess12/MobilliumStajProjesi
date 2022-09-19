package com.yusufmendes.basicnoteapp.model.NoteDetail

import com.google.gson.annotations.SerializedName

data class AddNote(

    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val data: AddNoteData,
    @SerializedName("message")
    val message: String,
)
