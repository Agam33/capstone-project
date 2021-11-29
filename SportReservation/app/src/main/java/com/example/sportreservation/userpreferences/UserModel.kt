package com.example.sportreservation.userpreferences

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    var name: String? = null,
    var email: String? = null,
    var address: String? = null,
    var phone: String? = null,
    var imgUrl: String? = null,
) : Parcelable
