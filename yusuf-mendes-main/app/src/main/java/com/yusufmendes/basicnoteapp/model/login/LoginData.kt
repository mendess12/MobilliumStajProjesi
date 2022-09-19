package com.yusufmendes.basicnoteapp.model.login

import com.google.gson.annotations.SerializedName

data class LoginData(

    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("token_type")
    val tokenType: String,
)
