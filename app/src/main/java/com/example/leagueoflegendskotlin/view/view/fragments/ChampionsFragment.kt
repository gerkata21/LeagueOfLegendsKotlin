package com.example.leagueoflegendskotlin.view.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leagueoflegendskotlin.R
import com.example.leagueoflegendskotlin.view.model.Resource
import com.example.leagueoflegendskotlin.view.view.adapters.ChampionAdapter
import com.example.leagueoflegendskotlin.view.viewmodel.LogInRegisterViewModel
import com.example.leagueoflegendskotlin.view.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_champions.*

@AndroidEntryPoint
class ChampionsFragment : Fragment(R.layout.fragment_champions) {

    private val mainViewModel: MainActivityViewModel by activityViewModels()
    private val logInRegisterViewModel: LogInRegisterViewModel by activityViewModels()

    lateinit var mAdapter: ChampionAdapter

    private val TAG = "Champions_Fragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        logInRegisterViewModel.getLoggedInMutableLiveData().observe(viewLifecycleOwner, Observer { user ->
            if (user) {
                setupRecyclerView()
                getChampionsData()
                updateChampionDb()
            } else if (!user){
                navController.navigate(R.id.loginFragment)
            }
        })
    }

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

    override fun onResume() {
        super.onResume()
        mAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("champion", it)
            }
            findNavController().navigate(
                R.id.action_championsFragment_to_championFragment,
                bundle
            )
        }
    }

    private fun setupRecyclerView() = champion_rv.apply{
        mAdapter = ChampionAdapter()
        adapter = mAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun updateChampionDb(){
        mainViewModel.championData.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        Toast.makeText(activity,"Database Updated",Toast.LENGTH_SHORT)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An Error Occurred: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun getChampionsData() {
        mainViewModel.getChampionsSortedByName.observe(viewLifecycleOwner, Observer {
            mAdapter.differ.submitList(it)
        })
    }

    private fun hideProgressBar(){ ProgressBar.visibility = View.INVISIBLE }

    private fun showProgressBar(){ ProgressBar.visibility = View.VISIBLE }

}
