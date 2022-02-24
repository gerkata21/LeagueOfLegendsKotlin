package com.example.leagueoflegendskotlin.view.view.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import com.example.leagueoflegendskotlin.R
import com.example.leagueoflegendskotlin.view.viewmodel.LogInRegisterViewModel
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment(R.layout.login_fragment) {
    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }

    private val viewModel : LogInRegisterViewModel by activityViewModels()
    private lateinit var savedStateHandle: SavedStateHandle


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(LOGIN_SUCCESSFUL, false)

        val navController = findNavController()

        viewModel.getUserMutableLiveData().observe(viewLifecycleOwner, Observer { user ->
            if(user.email != null){
                navController.navigate(R.id.championsFragment)
            } else {
                navController.navigate(R.id.loginFragment)
            }
        })

        //Initialize Views
        val mAlreadyHaveAccountTv = alreadyHaveAccount
        val mLoginBtn = loginBtn
        val mEmailEt = Login_Email_Et
        val mPasswordEt = Login_Password_Et

        //Login Button Click Listener

        mLoginBtn.setOnClickListener {
            var email = mEmailEt.text.toString()
            var password = mPasswordEt.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(email, password)
                savedStateHandle.set(LOGIN_SUCCESSFUL, true)
            }

        }

        //Go Back To Register Activity
        mAlreadyHaveAccountTv.setOnClickListener {
            navController.navigate(R.id.registerFragment)
        }
    }
}