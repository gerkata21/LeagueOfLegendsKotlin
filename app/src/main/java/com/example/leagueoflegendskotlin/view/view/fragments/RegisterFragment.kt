package com.example.leagueoflegendskotlin.view.view.fragments


import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.leagueoflegendskotlin.R
import com.example.leagueoflegendskotlin.view.viewmodel.LogInRegisterViewModel
import kotlinx.android.synthetic.main.register_fragment.*

class RegisterFragment : Fragment(R.layout.register_fragment) {

    //Initialize ViewModel
    private val viewModel : LogInRegisterViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController()

        val currentBackStackEntry = navController.currentBackStackEntry!!
        val savedStateHandle = currentBackStackEntry.savedStateHandle
        savedStateHandle.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)
            .observe(currentBackStackEntry, Observer { success ->
                if(!success){
                    val startDestination = navController.graph.startDestinationId
                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(startDestination, true)
                        .build()
                    navController.navigate(startDestination,null, navOptions)
                }
            })
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialize Views
        val mEmailEt = register_email_et
        val mPasswordEt = Password_Id_Et
        val mPasswordRepeatEt = Password_Id2_Et
        val mErrorTextMessage = error_message_text
        val mCreateAccountBtn = createAccountBtn
        val mErrorMessage = error_Message_Frame
        val mAlreadyHaveAccTv = alreadyHaveAccount

        //Already Have Account <TextView> ClickListener
        mAlreadyHaveAccTv.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        //Create Account <Button> ClickListener
        mCreateAccountBtn.setOnClickListener {
            var email = mEmailEt.text.toString()
            var password = mPasswordEt.text.toString()
            var passwordRepeat = mPasswordRepeatEt.text.toString()
            val checkPassword = viewModel.userInputMeetRequirements(password, passwordRepeat)

            if (checkPassword) {
                viewModel.register(email, password)
                findNavController().navigate(R.id.championsFragment)
            } else {
                mErrorTextMessage.text = ("Password Does not meet criteria")
                mErrorMessage.visibility = View.VISIBLE
            }
        }
    }

}