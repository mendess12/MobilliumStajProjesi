package com.yusufmendes.basicnoteapp.model.profile

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val profileData: ProfileData,
    @SerializedName("message")
    val message: String,
)
