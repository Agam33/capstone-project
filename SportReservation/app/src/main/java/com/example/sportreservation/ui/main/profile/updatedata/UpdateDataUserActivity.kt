package com.example.sportreservation.ui.main.profile.updatedata

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sportreservation.databinding.ActivityUpdateDataUserBinding
import com.example.sportreservation.ui.main.MainActivity
import com.example.sportreservation.userpreferences.UserModel
import com.example.sportreservation.userpreferences.UserPreference
import com.example.sportreservation.utils.setNotification

class UpdateDataUserActivity : AppCompatActivity() {

    private var _updateDataUserActivity: ActivityUpdateDataUserBinding? = null
    private val updateDataUserBinding get() = _updateDataUserActivity

    private lateinit var userPreference: UserPreference

    private val updateDataUserViewModel by viewModels<UpdateDataUserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _updateDataUserActivity = ActivityUpdateDataUserBinding.inflate(layoutInflater)
        setContentView(updateDataUserBinding?.root)

        userPreference = UserPreference(this)

        updateDataUserBinding?.changeBtn?.setOnClickListener {
            validateInput()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _updateDataUserActivity = null
    }

   private fun validateInput() = with(updateDataUserBinding!!) {
        var name = addEdName.text.toString().trim()
        var email = userPreference.getUser().email!!
        var address = addEdAddress.text.toString().trim()
        var phone = addEdPhone.text.toString().trim()

        if (!TextUtils.isDigitsOnly(phone)) {
            addEdPhone.error = FIELD_DIGIT_ONLY
            return
        }

        if (name.isEmpty()) {
            name = userPreference.getUser().name.toString()
        }

        if (address.isEmpty()) {
            address = userPreference.getUser().address.toString()
        }

        if (phone.isEmpty()) {
            phone = userPreference.getUser().phone.toString()
        }

        userPreference.setUser(
            UserModel(
                name,
                email,
                address,
                phone,
                userPreference.getUser().imgUrl
            )
        )

        val dataUser = HashMap<String, String>()
        dataUser["name"] = name
        dataUser["address"] = address
        dataUser["phone"] = phone

        updateDataUserViewModel.updateDataUserById(dataUser)

        setNotification(this@UpdateDataUserActivity, NOTIFICATION_TITLE, "")
    }

    companion object {
        private const val NOTIFICATION_TITLE = "Data berhasil diperbarui"
        private const val FIELD_DIGIT_ONLY = "Hanya boleh terisi angka"
    }
}