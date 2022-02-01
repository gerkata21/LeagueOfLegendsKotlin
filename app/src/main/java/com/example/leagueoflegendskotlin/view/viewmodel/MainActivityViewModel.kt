package com.example.leagueoflegendskotlin.view.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.leagueoflegendskotlin.view.db.Champion
import com.example.leagueoflegendskotlin.view.model.championsData.Champions
import com.example.leagueoflegendskotlin.view.model.Repository
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val repository: Repository,
    application: Application
) : ViewModel() {


    //Firebase
    private var userMutableLiveData = MutableLiveData<FirebaseUser>()
    private var loggedOutMutableLiveData = MutableLiveData<Boolean>()

    //Champions
    private val mutableChampionLiveData = MutableLiveData<Champions>()

    //LOCAL DATABASE
    val getChampionsSortedByName = repository.getAllChampionsSortedByName()


    var championLiveData: LiveData<Champions> = MutableLiveData<Champions>()
        get() = mutableChampionLiveData

    init {
        repository.repository(application)
        userMutableLiveData = repository.getMutableLiveData()
        loggedOutMutableLiveData = repository.getLoggedOutMutableLiveData()
    }

    fun getLoggedOutMutableLiveData() : MutableLiveData<Boolean>{
        return loggedOutMutableLiveData
    }

    fun logOut(){
        return repository.logOut()
    }

    fun getUser() : FirebaseUser{
        return repository.getUser()
    }

    //RESPONSE
    fun getChampionsAndSaveToDb(){
        viewModelScope.launch {
            val githubResponse = repository.getResponse()
            if (githubResponse!!.isSuccessful) {
                githubResponse.body()?.let {
                    for (item in it){
                        val champion = Champion(item.description,
                            item.icon,
                            item.name,
                            item.stats,
                            item.tags,
                            item.title
                        )
                        repository.insertChampionInDB(champion)
                    }
                }
            } else {
               //(TODO) HANDLE ERROR MESSAGE
            }
        }
    }

}