package com.example.sportreservation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sportreservation.databinding.ActivityFirstLaunchBinding
import com.example.sportreservation.ui.login.LoginActivity
import com.example.sportreservation.ui.main.MainActivity
import com.example.sportreservation.ui.regis.RegistrationActivity
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {

    private lateinit var firstLaunchActivity: ActivityFirstLaunchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstLaunchActivity = ActivityFirstLaunchBinding.inflate(layoutInflater)
        setContentView(firstLaunchActivity.root)

        firstLaunchActivity.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        firstLaunchActivity.btnRegis.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()

        val firebaseUser = FirebaseAuth.getInstance().currentUser

        if (firebaseUser != null) {
            startActivity(Intent(this@AuthActivity, MainActivity::class.java))
            finish()
        }
    }
}