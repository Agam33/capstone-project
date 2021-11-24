package com.example.sportreservation.data.source.local.entity

import androidx.room.Entity


@Entity
data class OrderListEntity(
    val id: Int,
    val name: String,
    val dueDate: Long,
    val imgUrl: String,
)