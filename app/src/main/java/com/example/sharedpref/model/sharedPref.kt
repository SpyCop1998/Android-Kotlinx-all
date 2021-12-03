package com.example.sharedpref.model

import com.google.gson.annotations.SerializedName

data class sharedPref(
    @SerializedName("userName")
    val userName: String,
    @SerializedName("password")
    val password: String
)
