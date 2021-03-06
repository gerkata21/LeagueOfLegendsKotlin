package com.example.leagueoflegendskotlin.view.model

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.example.leagueoflegendskotlin.view.db.Champion
import com.example.leagueoflegendskotlin.view.db.ChampionDao
import com.example.leagueoflegendskotlin.view.model.championsData.Champions
import com.example.leagueoflegendskotlin.view.model.network.LeagueAPIService
import com.example.retrofitmvvmexample.network.RetrofitClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject


class Repository @Inject constructor(
    private val championDao: ChampionDao,
    private var application: Application
) {

    private lateinit var userMutableLiveData : MutableLiveData<FirebaseUser>
    private lateinit var loggedInMutableData : MutableLiveData<Boolean>
    private lateinit var fAuth : FirebaseAuth

    fun repository(){
        fAuth = FirebaseAuth.getInstance()
        userMutableLiveData = MutableLiveData<FirebaseUser>()
        loggedInMutableData = MutableLiveData<Boolean>()
    }

    //FIREBASE
    //Register Call
    @RequiresApi(Build.VERSION_CODES.P)
    fun register(UserID : String, UserPassword: String){
        fAuth.createUserWithEmailAndPassword(UserID,UserPassword)
            .addOnCompleteListener(application.mainExecutor, { task ->
                    if(task.isSuccessful){
                        userMutableLiveData.postValue(fAuth.currentUser)
                    } else{
                        Toast.makeText(application,
                            "Registration Failed: " + task.exception!!.message,
                            Toast.LENGTH_LONG).show()
                    }
            })
    }

    //Login Call
    @RequiresApi(Build.VERSION_CODES.P)
    fun login (UserID: String, UserPassword: String){
        fAuth.signInWithEmailAndPassword(UserID, UserPassword)
            .addOnCompleteListener(application.mainExecutor, { task ->
                    if(task.isSuccessful) {
                        userMutableLiveData.postValue(fAuth.currentUser)
                        loggedInMutableData.postValue(true)
                    } else {
                        Toast.makeText(application,
                            "Login Failed: " + task.exception!!.message,
                            Toast.LENGTH_LONG).show()
                    }
            })
    }

    //Logout User
    fun logOut () {
        fAuth.signOut()
        loggedInMutableData.postValue(false)
    }

    //User Status - Logged/Logged Out
    fun getLoggedInMutableLiveData() : MutableLiveData<Boolean>{ return loggedInMutableData }

    //User List
    fun getMutableLiveData() : MutableLiveData<FirebaseUser>{ return userMutableLiveData }

    //Firebase CurrentUser
    fun getUser() : FirebaseUser? { return fAuth.currentUser }

    //RetrofitResponse
    suspend fun getResponse(): retrofit2.Response<Champions> {
        val retrofit = RetrofitClient.getRetrofitClient()
        val apiService = retrofit.create(LeagueAPIService::class.java)
        return apiService.getResponse()
    }

    fun getAllChampionsSortedByName() = championDao.getAllChampionsSortedByName()

    suspend fun insertChampionInDB(champion: Champion) = championDao.insertChampion(champion)

}