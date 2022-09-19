package com.yusufmendes.basicnoteapp.model.note

import com.google.gson.annotations.SerializedName

data class NoteData(

    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("data")
    val data: MutableList<NoteDataList>,
    @SerializedName("first_page_url")
    val firstPageUrl: String,
    @SerializedName("from")
    val from: Int,
    @SerializedName("last_page")
    val lastPage: Int,
    @SerializedName("last_page_url")
    val lastPageUrl: String,
    //@SerializedName("links")
    //val links : ArrayList<NoteLink>,
    @SerializedName("next_page_url")
    val nextPageUrl: String? = null,
    @SerializedName("path")
    val path: String = "http://basic-note-app-yusuf.herokuapp.com/api/users/me/notes",
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("prev_page_url")
    val prevPageUrl: String? = null,
    @SerializedName("to")
    val to: Int,
    @SerializedName("total")
    val total: Int,
)
