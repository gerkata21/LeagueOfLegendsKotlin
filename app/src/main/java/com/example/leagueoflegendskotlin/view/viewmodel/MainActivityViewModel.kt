package com.example.leagueoflegendskotlin.view.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.leagueoflegendskotlin.view.db.Champion
import com.example.leagueoflegendskotlin.view.model.championsData.Champions
import com.example.leagueoflegendskotlin.view.model.Repository
import com.example.leagueoflegendskotlin.view.model.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    //Firebase
    private var userMutableLiveData = MutableLiveData<FirebaseUser>()
    private var loggedOutMutableLiveData = MutableLiveData<Boolean>()

    //LOCAL DATABASE
    val getChampionsSortedByName = repository.getAllChampionsSortedByName()

    val championData: MutableLiveData<Resource<Champions>> = MutableLiveData()

    init {
        repository.repository()
        userMutableLiveData = repository.getMutableLiveData()
        loggedOutMutableLiveData = repository.getLoggedOutMutableLiveData()
        getChampionsAndSaveToDb()
    }

    fun getLoggedOutMutableLiveData(): MutableLiveData<Boolean> {
        return loggedOutMutableLiveData
    }

    fun logOut() {
        return repository.logOut()
    }

    fun getUser(): FirebaseUser {
        return repository.getUser()
    }

    //RESPONSE
    private fun getChampionsAndSaveToDb() {
        viewModelScope.launch {

            championData.postValue(Resource.Loading())
            val githubResponse = repository.getResponse()
            championData.postValue(handleChampionsResponse(githubResponse))

            if (githubResponse!!.isSuccessful) {
                githubResponse.body()?.let {
                    for (item in it) {
                        val champion = Champion(
                            item.description,
                            item.icon,
                            item.name,
                            item.stats,
                            item.tags,
                            item.title
                        )
                        repository.insertChampionInDB(champion)
                    }
                    championData.postValue(Resource.Success(it))
                }
            } else {
                championData.postValue(Resource.Error(githubResponse.message()))
            }

        }
    }

    private fun handleChampionsResponse(response: Response<Champions>) : Resource<Champions>{
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}