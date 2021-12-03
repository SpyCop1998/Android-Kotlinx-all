package com.example.sharedpref.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharedpref.model.sharedPref
import com.example.sharedpref.utils.Resource
import kotlinx.coroutines.launch

class SplashFragmentViewModel():ViewModel() {
    private val sharedPrefLiveData= MutableLiveData<Resource<sharedPref>>()
    fun getSharedPref(sharedPreferences: SharedPreferences){
        viewModelScope.launch {
            try{
                val userName= sharedPreferences.getString("userName","default")

                val password= sharedPreferences.getString("password","default")
                sharedPrefLiveData.postValue(Resource.success(sharedPref(userName.toString(),password.toString())))
            }catch (e:Exception){
                print(e.toString())
            }
        }
    }

    fun getSharedPrefLiveData():MutableLiveData<Resource<sharedPref>>{
        return sharedPrefLiveData
    }
}

