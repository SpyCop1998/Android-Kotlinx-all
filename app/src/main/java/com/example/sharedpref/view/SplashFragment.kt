package com.example.sharedpref.view
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.sharedpref.R

class SplashFragment : Fragment() {
    private val sharedPrefFile = "kotlinsharedpreference"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_splash, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences: SharedPreferences =
            activity?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)!!
        val sharedNameValue = sharedPreferences.getString("Key", "defaultname")
        if (sharedNameValue == "") {
            val navController: NavController=NavHostFragment.findNavController(this)
            navController.navigate(R.id.action_splashFragment_to_homeFragment)
        } else {
            val navController: NavController=NavHostFragment.findNavController(this)
            navController.navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
}