package com.example.leagueoflegendskotlin.view.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import com.example.leagueoflegendskotlin.R
import com.example.leagueoflegendskotlin.view.model.Repository
import com.example.leagueoflegendskotlin.view.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_champions.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
                    viewModel.logOut()
                    handleUserLogOut()
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

    private fun handleUserLogOut(){
        viewModel.getLoggedOutMutableLiveData().observe(this,{
            if(it){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        })
    }

    //Initialize Views
    private fun initializeViews(){
        if(viewModel.getUser().email != null) {
            tvUserId.text = ("Logged in As: " + viewModel.getUser().email)
        }
    }

}


