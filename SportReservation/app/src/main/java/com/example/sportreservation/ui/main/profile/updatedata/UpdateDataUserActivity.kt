package com.example.sportreservation.ui.main.profile.updatedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.sportreservation.databinding.ActivityUpdateDataUserBinding
import com.example.sportreservation.ui.main.MainActivity
import com.example.sportreservation.userpreferences.UserModel
import com.example.sportreservation.userpreferences.UserPreference
import com.example.sportreservation.utils.isValidEmail
import com.example.sportreservation.utils.setNotification

class UpdateDataUserActivity : AppCompatActivity() {

    private var _updateDataUserActivity: ActivityUpdateDataUserBinding? = null
    private val updateDataUserBinding get() =  _updateDataUserActivity

    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _updateDataUserActivity = ActivityUpdateDataUserBinding.inflate(layoutInflater)
        setContentView(updateDataUserBinding?.root)

        userPreference = UserPreference(this)

        updateDataUserBinding?.changeBtn?.setOnClickListener {
            validateInput()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun validateInput() = with(updateDataUserBinding?.inputLayout!!) {

        var name = addEdName.text.toString().trim()
        var email = addEdEmail.text.toString().trim()
        var address = addEdAddress.text.toString().trim()
        var phone = addEdPhone.text.toString().trim()

        if(!isValidEmail(email)) {
            addEdEmail.error = FIELD_IS_NOT_VALID
            return
        }

        if(!TextUtils.isDigitsOnly(phone)) {
            addEdPhone.error = FIELD_DIGIT_ONLY
            return
        }

        if(name.isEmpty()) {
            name = userPreference.getUser().name.toString()
        }

        if(address.isEmpty()) {
            address = userPreference.getUser().address.toString()
        }

        if(phone.isEmpty()) {
            phone = userPreference.getUser().phone.toString()
        }

        userPreference.setUser(UserModel(
            name,
            email,
            address,
            phone,
            userPreference.getUser().imgUrl
        ))

        setNotification(this@UpdateDataUserActivity, NOTIFICATION_TITLE, "")
    }

    override fun onDestroy() {
        super.onDestroy()
        _updateDataUserActivity = null
    }
    companion object {
        private const val NOTIFICATION_TITLE = "Data berhasil diperbarui"
        private const val FIELD_DIGIT_ONLY = "Hanya boleh terisi angka"
        private const val FIELD_IS_NOT_VALID = "Email tidak valid"
    }
}