package com.example.sportreservation.data.source.entity

import androidx.room.Entity


@Entity
class UserEntity(
    val id: Int,
    val name: String,
    val phone: String,
    val cash: Long,
    val imgUrl: String
)