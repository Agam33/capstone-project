package com.example.sportreservation.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class SportPlaceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val address: String,
    val phone: String,
    val open: String,
    val close: String,
    val cost: String,
    val facility: String,
    val imgUrl: String,
    val sportName: String
) : Parcelable