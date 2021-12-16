package com.example.sportreservation.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EquipmentResponse(
    val id: Int,
    val name: String,
    val price: String,
    val imgUrl: String
) : Parcelable
