package com.example.sportreservation.data.source.local.entity

import androidx.room.Entity
import com.example.sportreservation.utils.OrderStatus

@Entity
data class HistoryEntity(
    val id: Int,
    val name: String,
    val status: OrderStatus,
    val date: String,
    val imgUrl: String,
)