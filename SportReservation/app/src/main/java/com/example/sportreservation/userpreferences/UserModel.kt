package com.example.sportreservation.userpreferences

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val name: String? = null,
    val email: String? = null,
    val address: String? = null,
    val phone: String? = null,
    val imgUrl: String? = null,
) : Parcelable
