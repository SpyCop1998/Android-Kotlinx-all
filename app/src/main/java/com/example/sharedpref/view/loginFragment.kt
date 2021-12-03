package com.example.sharedpref.view
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.sharedpref.R
import com.example.sharedpref.databinding.FragmentLoginBinding
import com.example.sharedpref.model.User
import com.example.sharedpref.network.ApiHelperImpl
import com.example.sharedpref.network.RetrofitBuilder
import com.example.sharedpref.utils.Status
import com.example.sharedpref.utils.ViewModelFactory
import com.example.sharedpref.viewmodel.LoginFragmentViewModel
class loginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: LoginFragmentViewModel
    lateinit var navController: NavController
    var isLoginSuccessfull: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)
        setUpViewModel()
        setupObserver()
        binding.singupbBtn.setOnClickListener{
            navController.navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.loginBtn.setOnClickListener {
            val userName:String=binding.userName.text.toString()
            val password:String=binding.password.text.toString()
            if(userName.isNotEmpty() && password.isNotEmpty()) {
                val user = User(userName = userName, password = password)
                viewModel.login(user)
            }else{
                Toast.makeText(activity,"Empty Fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUpViewModel() {
        viewModel =
            ViewModelProviders.of(this, ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService)))
                .get(LoginFragmentViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.getLoginResponse().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Xmen from success", it.status.toString())
                    if (it.data?.response!!) {
                        isLoginSuccessfull=true
                        Toast.makeText(activity,"User Logged In Successfully", Toast.LENGTH_SHORT).show()
                        navController.navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                    Log.d("Xmen from error", it.status.toString())
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("Xmen from loading", it.status.toString())
                }
            }
        })
    }
}