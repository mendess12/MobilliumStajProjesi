package com.yusufmendes.basicnoteapp.model.update

import com.google.gson.annotations.SerializedName

data class UpdateNoteData(

    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("note")
    val note: String,
)
