package com.example.leagueoflegendskotlin.view.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.leagueoflegendskotlin.R
import com.example.leagueoflegendskotlin.view.viewmodel.LogInRegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel : LogInRegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Initialize Views
        val mAlreadyHaveAccountTv = findViewById<TextView>(R.id.alreadyHaveAccount)
        val mLoginBtn = findViewById<Button>(R.id.loginBtn)
        val mEmailEt = findViewById<EditText>(R.id.Login_Email_Et)
        val mPasswordEt = findViewById<EditText>(R.id.Login_Password_Et)

        //Login Button Click Listener
        mLoginBtn.setOnClickListener {
            var email = mEmailEt.text.toString()
            var password = mPasswordEt.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(email, password)
            }
        }

        //If Account is logged in then log in
        viewModel.getUserMutableLiveData().observe(this,{
            if(it.email != null) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        })

        //Go Back To Register Activity
        mAlreadyHaveAccountTv.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }
    }
}