package com.example.sportreservation.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sportreservation.databinding.ActivityLoginBinding
import com.example.sportreservation.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var loginActivity: ActivityLoginBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginActivity = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginActivity.root)

        firebaseAuth = FirebaseAuth.getInstance()

        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loginActivity.btnLogin.setOnClickListener {
            validateInput()
        }
    }

    private fun validateInput() {
        val email = loginActivity.edtEmail.text.toString()
        val pass = loginActivity.edtPassword.text.toString()

        if(email.isEmpty()) {
            loginActivity.edtEmail.error = FIELD_REQUIRED
            return
        }

        if(pass.isEmpty()) {
            loginActivity.edtPassword.error = FIELD_REQUIRED
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Salah password atau email", Toast.LENGTH_LONG).show()
                }
            }
    }

    companion object {
        private const val FIELD_REQUIRED = "Field tidak boleh kosong"
    }
}