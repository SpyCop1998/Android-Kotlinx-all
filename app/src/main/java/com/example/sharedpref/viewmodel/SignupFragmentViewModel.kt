package com.example.sharedpref.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharedpref.model.User
import com.example.sharedpref.model.ApiResponse
import com.example.sharedpref.network.ApiHelper
import com.example.sharedpref.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class SignupFragmentViewModel(private val apiHelper: ApiHelper):ViewModel() {
    private val apiResponse= MutableLiveData<Resource<ApiResponse>>()
    public fun signup(user: User){
        viewModelScope.launch {
            apiResponse.postValue(Resource.loading(null))
            try {
                val response=apiHelper.signupUser(user)
                apiResponse.postValue(Resource.success(response))
            }catch (e:Exception){
                apiResponse.postValue(Resource.loading(null))
            }
        }
    }

    fun getSignupResponse():LiveData<Resource<ApiResponse>>{
        return apiResponse
    }
}