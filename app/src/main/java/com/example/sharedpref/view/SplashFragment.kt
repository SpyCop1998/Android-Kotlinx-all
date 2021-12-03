package com.example.sharedpref.view
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.sharedpref.R
import com.example.sharedpref.utils.Status
import com.example.sharedpref.viewmodel.SplashFragmentViewModel

class SplashFragment : Fragment() {
    val sharedPrefFile = "kotlinsharedpreference"
    lateinit var viewModel: SplashFragmentViewModel
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_splash, container, false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupObserver()
        val sharedPreferences: SharedPreferences =
            activity?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)!!
        viewModel.getSharedPref(sharedPreferences)

        navController=Navigation.findNavController(view)
    }

    private fun setupViewModel(){
        viewModel=ViewModelProviders.of(this).get(SplashFragmentViewModel::class.java)
    }

    private fun setupObserver(){
        viewModel.getSharedPrefLiveData().observe(viewLifecycleOwner,{
            when(it.status){
                Status.SUCCESS -> {
                    Log.d("Xmen from success", it.status.toString())
                    if (it.data!!.userName=="default"){
                        navController.navigate(R.id.action_splashFragment_to_loginFragment)
                    }else{
                        navController.navigate(R.id.action_splashFragment_to_homeFragment)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(activity,"Failed", Toast.LENGTH_SHORT).show()

                    Log.d("Xmen from error", it.status.toString())
                }
                Status.LOADING -> {

                    Log.d("Xmen from loading", it.status.toString())
                }
            }
        })
    }
}