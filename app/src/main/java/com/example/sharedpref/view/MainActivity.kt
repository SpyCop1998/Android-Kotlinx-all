package com.example.sharedpref.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.sharedpref.R
import com.example.sharedpref.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val finalHost = NavHostFragment.create(R.navigation.nav_graph)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.nav_host,finalHost)
//            .setPrimaryNavigationFragment(finalHost)
//            .commit()
    }
}