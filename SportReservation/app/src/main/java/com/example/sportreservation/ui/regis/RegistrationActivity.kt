package com.example.sportreservation.ui.regis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.sportreservation.databinding.ActivityRegistrationBinding
import com.example.sportreservation.ui.main.MainActivity
import com.example.sportreservation.userpreferences.UserModel
import com.example.sportreservation.userpreferences.UserPreference
import com.example.sportreservation.utils.isValidEmail

class RegistrationActivity : AppCompatActivity() {

    private var _registrationBinding: ActivityRegistrationBinding? = null
    private val registrationBinding get() = _registrationBinding

    private lateinit var userPreferences: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _registrationBinding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(registrationBinding?.root)

        userPreferences = UserPreference(this)

        isUserRegis()

        registrationBinding?.btnRegis?.setOnClickListener {
            checkEmptyField()
        }
    }

    private fun setUserInput(name: String, email: String, address: String, phone: String)  {
        val userModel = UserModel()
        userModel.let {
            it.name = name
            it.email = email
            it.address = address
            it.phone = phone
            it.imgUrl = ""
        }

        userPreferences.setUser(userModel)
    }

    private fun checkEmptyField() = with(registrationBinding?.register!!) {
        val name = addEdName.text.toString().trim()
        val email = addEdEmail.text.toString().trim()
        val address = addEdAddress.text.toString().trim()
        val phone = addEdPhone.text.toString().trim()

        if(name.isEmpty()) {
            addEdName.error = FIELD_REQUIRED
            return
        }

        if(!isValidEmail(email)) {
            addEdEmail.error = FIELD_IS_NOT_VALID
            return
        }

        if(address.isEmpty()) {
            addEdAddress.error = FIELD_REQUIRED
            return
        }

        if(phone.isEmpty()) {
            addEdPhone.error = FIELD_REQUIRED
            return
        }

        if(!TextUtils.isDigitsOnly(phone)) {
            addEdPhone.error = FIELD_DIGIT_ONLY
            return
        }

        setUserInput(name, email, address, phone)
        isUserRegis()

    }

    private fun isUserRegis() {
        if(userPreferences.isUserRegis()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    companion object {
        private const val FIELD_REQUIRED = "Field tidak boleh kosong"
        private const val FIELD_DIGIT_ONLY = "Hanya boleh terisi angka"
        private const val FIELD_IS_NOT_VALID = "Email tidak valid"
    }

    override fun onDestroy() {
        super.onDestroy()
        _registrationBinding = null
    }
}