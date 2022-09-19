package com.yusufmendes.basicnoteapp.model.NoteDetail

import com.google.gson.annotations.SerializedName

data class AddNoteData(

    @SerializedName("title")
    val noteTitle: String,
    @SerializedName("note")
    val noteDetail: String,
    @SerializedName("id")
    val id: Int,
)
