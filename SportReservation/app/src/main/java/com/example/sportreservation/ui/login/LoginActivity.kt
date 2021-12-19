package com.example.sportreservation.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sportreservation.databinding.ActivityLoginBinding
import com.example.sportreservation.ui.main.MainActivity
import com.example.sportreservation.ui.regis.RegistrationActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var loginActivity: ActivityLoginBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginActivity = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginActivity.root)

        firebaseAuth = FirebaseAuth.getInstance()

        loginActivity.btnLogin.setOnClickListener {
            validateInput()
        }

        loginActivity.btnSignUp.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()

        val firebaseUser = FirebaseAuth.getInstance().currentUser

        if (firebaseUser != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

    private fun validateInput() {
        val email = loginActivity.edtEmail.text.toString()
        val pass = loginActivity.edtPassword.text.toString()

        if (email.isEmpty()) {
            loginActivity.edtEmail.error = FIELD_REQUIRED
            return
        }

        if (pass.isEmpty()) {
            loginActivity.edtPassword.error = FIELD_REQUIRED
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Wrong password or email",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    companion object {
        private const val FIELD_REQUIRED = "Field cannot be empty"
    }
}