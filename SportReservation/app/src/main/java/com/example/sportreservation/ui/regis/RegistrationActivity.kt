package com.example.sportreservation.ui.regis

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sportreservation.databinding.ActivityRegistrationBinding
import com.example.sportreservation.ui.login.LoginActivity
import com.example.sportreservation.userpreferences.UserModel
import com.example.sportreservation.userpreferences.UserPreference
import com.example.sportreservation.utils.isValidEmail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {

    private var _registrationBinding: ActivityRegistrationBinding? = null
    private val registrationBinding get() = _registrationBinding

    private lateinit var userPreferences: UserPreference

    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _registrationBinding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(registrationBinding?.root)

        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        userPreferences = UserPreference(this)

        auth = FirebaseAuth.getInstance()

        registrationBinding?.btnRegis?.setOnClickListener {
            checkEmptyField()
            startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun setUserInput(
        name: String,
        email: String,
        address: String,
        phone: String,
        password: String
    ) {

        val userModel = UserModel()
        userModel.let { user ->
            user.name = name
            user.email = email
            user.address = address
            user.phone = phone
            user.imgUrl = ""
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {

                    val firebaseUser = auth.currentUser
                    val userId = firebaseUser?.uid

                    userPreferences.setUser(userModel)

                    dbRef = FirebaseDatabase.getInstance().getReference("Users").child(userId!!)

                    val dataUser = HashMap<String, String>()
                    dataUser["id"] = userId
                    dataUser["name"] = name
                    dataUser["email"] = email
                    dataUser["address"] = address
                    dataUser["phone"] = phone

                    dbRef.setValue(dataUser)
                } else {
                    Toast.makeText(this@RegistrationActivity, "Gagal mendaftar", Toast.LENGTH_LONG)
                        .show()
                }
            }
    }

    private fun checkEmptyField() = with(registrationBinding?.register!!) {
        val name = addEdName.text.toString().trim()
        val email = addEdEmail.text.toString().trim()
        val address = addEdAddress.text.toString().trim()
        val phone = addEdPhone.text.toString().trim()
        val password = registrationBinding?.addPassword?.text.toString().trim()

        if (name.isEmpty()) {
            addEdName.error = FIELD_REQUIRED
            return
        }

        if (!isValidEmail(email)) {
            addEdEmail.error = FIELD_IS_NOT_VALID
            return
        }

        if (address.isEmpty()) {
            addEdAddress.error = FIELD_REQUIRED
            return
        }

        if (phone.isEmpty()) {
            addEdPhone.error = FIELD_REQUIRED
            return
        }

        if (!TextUtils.isDigitsOnly(phone)) {
            addEdPhone.error = FIELD_DIGIT_ONLY
            return
        }

        if (password.length < 7) {
            registrationBinding?.addPassword?.error = PASSWORD_REQUIRED_LENGTH
            return
        }

        setUserInput(name, email, address, phone, password)

    }

    companion object {
        private const val FIELD_REQUIRED = "Field tidak boleh kosong"
        private const val FIELD_DIGIT_ONLY = "Hanya boleh terisi angka"
        private const val FIELD_IS_NOT_VALID = "Email tidak valid"
        private const val PASSWORD_REQUIRED_LENGTH = "Password harus lebih dari 6 karakter"
    }

    override fun onDestroy() {
        super.onDestroy()
        _registrationBinding = null
    }
}