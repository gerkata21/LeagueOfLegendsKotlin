package com.example.leagueoflegendskotlin.view.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leagueoflegendskotlin.R
import com.example.leagueoflegendskotlin.view.db.ChampionDao
import com.example.leagueoflegendskotlin.view.util.CHAMPION_DATABASE_NAME
import com.example.leagueoflegendskotlin.view.view.adapters.ChampionAdapter
import com.example.leagueoflegendskotlin.view.viewmodel.MainActivityViewModel
import com.example.leagueoflegendskotlin.view.util.ChampionClickListener
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ChampionClickListener {

    @Inject
    lateinit var championDao: ChampionDao

    private val viewModel : MainActivityViewModel by viewModels()

    private lateinit var mChampionRv: RecyclerView
    private lateinit var mAdapter: ChampionAdapter

    private lateinit var mUserTv: TextView
    private lateinit var mMenu: ImageButton
    private lateinit var mProgressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupRecyclerView()

        viewModel.getChampionsSortedByName.observe(this, Observer {
            mAdapter.submitList(it)
        })

        // TODO: I am waiting for more functionality ;)
        //POPUP MENU
        mMenu.setOnClickListener{
            val popup = PopupMenu(this@MainActivity, mMenu)
            popup.menuInflater.inflate(R.menu.popup_menu,popup.menu)
            popup.setOnMenuItemClickListener { item ->

                if(item.title.equals("Account")){
                    Toast.makeText(application,
                        "Account",
                        Toast.LENGTH_SHORT).show()
                }

                if(item.title.equals("Settings")){
                    Toast.makeText(application,
                        "Settings",
                        Toast.LENGTH_SHORT).show()
                }

                if(item.title.equals("Logout")){

                    viewModel.logOut()

                    Toast.makeText(application,
                        "Logout",
                        Toast.LENGTH_SHORT).show()
                }
                true
            }
            popup.show()
        }

        //WHEN USER LOGS OUT
        viewModel.getLoggedOutMutableLiveData().observe(this,{
            if(it){
                startActivity(Intent(this@MainActivity,LoginActivity::class.java))
                finish()
            }
        })

        //Makes network Call
        viewModel.getChampionsAndSaveToDb()

    }

    private fun setupRecyclerView() = mChampionRv.apply{
        mAdapter = ChampionAdapter()
        adapter = mAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

    //Initialize Views
    private fun initializeViews(){
        mChampionRv = findViewById(R.id.champion_rv)
        mMenu = findViewById(R.id.DropDownMenu)
        mUserTv = findViewById(R.id.UserId_Tv)
        mProgressBar = findViewById(R.id.ProgressBar)

        if(viewModel.getUser().email != null) {
            mUserTv.text = ("Logged in As: " + viewModel.getUser().email)
        }
        mChampionRv.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    //EmailClicked Function
    override fun onChampionClicked(view: View?, position: Int) {

        Intent(this,CharacterDetails::class.java)
            .also{
            characterIntent ->
            characterIntent.putExtra("champion", viewModel.championLiveData.value!![position] as Serializable)
            startActivity(characterIntent)
        }
    }


}