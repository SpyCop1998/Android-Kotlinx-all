package com.example.sharedpref.network

import com.example.sharedpref.model.ApiResponse
import com.example.sharedpref.model.User

class ApiHelperImpl(private val apiService: ApiService):ApiHelper{
    override suspend fun signupUser(user: User) =apiService.signupUser(user)
    override suspend fun loginUser(user: User)=apiService.loginUser(user)
}