package com.example.sportreservation.ui.main

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sportreservation.R
import com.example.sportreservation.databinding.ActivityMainBinding
import com.example.sportreservation.setting.SettingActivity
import com.example.sportreservation.ui.order.OrderActivity
import com.example.sportreservation.ui.rental.RentalActivity
import com.example.sportreservation.userpreferences.UserModel
import com.example.sportreservation.userpreferences.UserPreference
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0f

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home,
            R.id.navigation_article,
            R.id.navigation_rent,
            R.id.navigation_history,
            R.id.navigation_profile
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()

        val firebaseUser = FirebaseAuth.getInstance().currentUser

        if(firebaseUser != null) {

            val dbRef = firebaseUser.uid.let {
                FirebaseDatabase.getInstance().getReference("Users").child(
                    it
                )
            }
            dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userPreference = UserPreference(this@MainActivity)
                    val dataSnap = snapshot.getValue(UserModel::class.java)
                    userPreference.setEmail(dataSnap?.email)
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_setting -> {
                startActivity(Intent(this, SettingActivity::class.java))
                true
            }
            R.id.action_reservasi -> {
                startActivity(Intent(this, OrderActivity::class.java))
                true
            }
            R.id.action_rental -> {
                startActivity(Intent(this, RentalActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}