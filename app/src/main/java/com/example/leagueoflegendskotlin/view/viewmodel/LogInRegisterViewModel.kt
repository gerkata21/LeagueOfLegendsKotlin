package com.example.leagueoflegendskotlin.view.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leagueoflegendskotlin.view.model.Repository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogInRegisterViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    //private var repository = Repository()
    private var userMutableLiveData = MutableLiveData<FirebaseUser>()
    private var loggedInMutableLiveData = MutableLiveData<Boolean>()

    init {
        repository.repository()
        loggedInMutableLiveData = repository.getLoggedInMutableLiveData()
        userMutableLiveData = repository.getMutableLiveData()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun register(userID: String, userPass: String) {
        repository.register(userID, userPass)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun login (userID: String,userPass: String){
        repository.login(userID,userPass)
    }

    fun getLoggedInMutableLiveData(): MutableLiveData<Boolean> {
        return loggedInMutableLiveData
    }


    fun getUserMutableLiveData(): MutableLiveData<FirebaseUser> {
        return userMutableLiveData
    }

    //Check if passwords meet criteria
    fun userInputMeetRequirements(strPass: String, strPass2: String): Boolean {

        var capitalLetter = false
        var lowercaseLetter = false
        var numberCheck = false
        var passwordLength = false

        if (strPass == strPass2) {
            for (i in strPass.indices) {
                if (strPass.length > 7) {
                    passwordLength = true
                }
                var ch = strPass[i]
                if (Character.isDigit(ch)) {
                    numberCheck = true
                }
                if (Character.isUpperCase(ch)) {
                    capitalLetter = true
                }
                if (Character.isLowerCase(ch)) {
                    lowercaseLetter = true
                }
            }
        } else return false
        return numberCheck && capitalLetter && lowercaseLetter && passwordLength
    }

}

