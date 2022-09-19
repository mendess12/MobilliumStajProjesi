package com.yusufmendes.basicnoteapp.model.note

import com.google.gson.annotations.SerializedName

data class NoteLink(

    @SerializedName("url")
    val url: String? = null,
    @SerializedName("label")
    val label: String,
    @SerializedName("active")
    val active: Boolean,
)
