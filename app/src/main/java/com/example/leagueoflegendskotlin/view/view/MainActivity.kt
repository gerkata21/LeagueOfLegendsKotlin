package com.example.leagueoflegendskotlin.view.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.leagueoflegendskotlin.R
import com.example.leagueoflegendskotlin.view.viewmodel.LogInRegisterViewModel
import com.example.leagueoflegendskotlin.view.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_champions.*
import kotlinx.android.synthetic.main.toolbar.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val mainViewModel: MainActivityViewModel by viewModels()
    private val logInRegisterViewModel : LogInRegisterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        initializeViews()

        // TODO: I am waiting for more functionality ;)
        //POPUP MENU
        DropDownMenu.setOnClickListener {
            val popup = PopupMenu(this@MainActivity, DropDownMenu)
            popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)
            popup.setOnMenuItemClickListener { item ->

                if (item.title.equals("Account")) {
                    Toast.makeText(
                        application,
                        "Account",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if (item.title.equals("Settings")) {
                    Toast.makeText(
                        application,
                        "Settings",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if (item.title.equals("Logout")) {
                    mainViewModel.logOut()
                    Toast.makeText(
                        application,
                        "Logout",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                true
            }
            popup.show()
        }
    }

    //Initialize Views
    private fun initializeViews(){
        logInRegisterViewModel.getUserMutableLiveData().observe(this,{ user ->
            tvUserId.text = ("Logged in As: ${user.email}")
        })
    }
}


