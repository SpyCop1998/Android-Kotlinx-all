package com.example.sharedpref.network

import com.example.sharedpref.model.User
import com.example.sharedpref.model.ApiResponse

interface ApiHelper {
    suspend fun signupUser(user: User):ApiResponse
    suspend fun loginUser(user: User):ApiResponse
}