package com.example.sharedpref.network

import com.example.sharedpref.model.User
import com.example.sharedpref.model.ApiResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("signup")
    suspend fun signupUser(@Body user:User):ApiResponse

    @POST("login")
    suspend fun loginUser(@Body user: User):ApiResponse
}