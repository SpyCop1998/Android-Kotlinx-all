package com.example.sharedpref.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitBuilder {
    private const val BASE_URL = "http://192.168.1.3:3000/"
    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService:ApiService= getRetrofit().create(ApiService::class.java)
}