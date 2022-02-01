package com.example.leagueoflegendskotlin.view.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.leagueoflegendskotlin.R
import com.example.leagueoflegendskotlin.view.viewmodel.LogInRegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //Initialize Views
        val mEmailEt = findViewById<EditText>(R.id.register_email_et)
        val mPasswordEt = findViewById<EditText>(R.id.Password_Id_Et)
        val mPasswordRepeatEt = findViewById<EditText>(R.id.Password_Id2_Et)
        val mErrorTextMessage = findViewById<TextView>(R.id.error_message_text)
        val mCreateAccountBtn = findViewById<Button>(R.id.createAccountBtn)
        val mErrorMessage = findViewById<RelativeLayout>(R.id.error_Message_Frame)
        val mAlreadyHaveAccTv = findViewById<TextView>(R.id.alreadyHaveAccount)

        //Initialize ViewModel
        val mLogInRegisterViewModel by lazy {
            ViewModelProvider(this)[LogInRegisterViewModel::class.java]
        }

        //Already Have Account <TextView> ClickListener
        mAlreadyHaveAccTv.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }

        //Create Account <Button> ClickListener
        mCreateAccountBtn.setOnClickListener {
            var email = mEmailEt.text.toString()
            var password = mPasswordEt.text.toString()
            var passwordRepeat = mPasswordRepeatEt.text.toString()
            val checkPassword =
                mLogInRegisterViewModel.userInputMeetRequirements(password, passwordRepeat)

            if (checkPassword) {
                mLogInRegisterViewModel.register(email, password)
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            } else {
                mErrorTextMessage.text = ("Password Does not meet criteria")
                mErrorMessage.visibility = View.VISIBLE
            }
        }
    }
}