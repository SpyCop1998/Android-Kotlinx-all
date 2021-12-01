package com.example.sharedpref.model

import android.text.Editable
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userName")
    val userName: String,
    @SerializedName("password")
    val password: String
)
