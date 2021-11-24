package com.example.sportreservation.data.source.local.entity

import androidx.room.Entity

@Entity
data class SportPlaceEntity (
    val id: Int,
    val name: String,
    val address: String,
    val phone: String,
    val open: String,
    val cost: String,
    val facility: String,
    val imgUrl: String,
)