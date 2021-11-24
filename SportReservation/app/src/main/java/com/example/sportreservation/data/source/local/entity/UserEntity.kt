package com.example.sportreservation.data.source.local.entity

import androidx.room.Entity


@Entity
data class UserEntity(
    val id: Int,
    val name: String,
    val phone: String,
    val imgUrl: String
)