package com.yusufmendes.basicnoteapp.model.note

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NoteDataList(

    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val noteTitle: String,
    @SerializedName("note")
    val noteDetail: String,
) : Parcelable

