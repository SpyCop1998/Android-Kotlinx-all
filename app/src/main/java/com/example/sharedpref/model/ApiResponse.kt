package com.example.sharedpref.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("response")
    val response:Boolean
)
