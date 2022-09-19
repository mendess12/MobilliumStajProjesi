package com.yusufmendes.basicnoteapp.model.profile

import com.google.gson.annotations.SerializedName

data class ProfileData(

    @SerializedName("id")
    val id: Int,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("email")
    val email: String,
)
