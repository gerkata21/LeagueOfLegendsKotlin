package com.example.leagueoflegendskotlin.view.viewmodel

import androidx.lifecycle.ViewModel
import com.example.leagueoflegendskotlin.view.db.Champion
import com.example.leagueoflegendskotlin.view.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {



}