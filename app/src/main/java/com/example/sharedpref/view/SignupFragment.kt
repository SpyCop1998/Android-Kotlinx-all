package com.example.sharedpref.view

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


import com.example.sharedpref.databinding.FragmentSignupBinding
import com.example.sharedpref.model.User
import com.example.sharedpref.network.ApiHelperImpl
import com.example.sharedpref.network.RetrofitBuilder
import com.example.sharedpref.utils.Status
import com.example.sharedpref.utils.ViewModelFactory
import com.example.sharedpref.viewmodel.SignupFragmentViewModel

class SignupFragment : Fragment() {
    lateinit var binding: FragmentSignupBinding
    lateinit var viewModel: SignupFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setupObserver()
        binding.signUp.setOnClickListener {
            val userName = binding.userName.text
            val password = binding.password.text
            val user = User(userName = "test", password = "test")
            viewModel.signup(user)
        }
    }

    private fun setUpViewModel() {
        viewModel =
            ViewModelProviders.of(this, ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService)))
                .get(SignupFragmentViewModel::class.java)
    }

    private fun setupObserver(){
        viewModel.getSignupResponse().observe(viewLifecycleOwner,{
            when(it.status){
                Status.SUCCESS -> {
                    binding.progressBar.visibility=View.GONE
                    Log.d("Xmen from success", it.status.toString())
                    if(it.data?.response!!){
                        Toast.makeText(activity,"User Added Successfully",Toast.LENGTH_SHORT).show()
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(activity,"Failed", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility=View.GONE
                    Log.d("Xmen from error", it.status.toString())
                }
                Status.LOADING -> {
                    binding.progressBar.visibility=View.VISIBLE
                    Log.d("Xmen from loading", it.status.toString())
                }
            }
        })
    }
}