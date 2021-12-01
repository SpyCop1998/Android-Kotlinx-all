package com.example.sharedpref.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sharedpref.network.ApiHelper
import com.example.sharedpref.viewmodel.LoginFragmentViewModel
import com.example.sharedpref.viewmodel.SignupFragmentViewModel

class ViewModelFactory(private val apiHelper: ApiHelper):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SignupFragmentViewModel::class.java)){
            return SignupFragmentViewModel(apiHelper) as T
        }
        if(modelClass.isAssignableFrom(LoginFragmentViewModel::class.java)){
            return LoginFragmentViewModel(apiHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}