package com.example.sportreservation.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportreservation.utils.OrderStatus

@Entity
data class OrderEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val sportName: String,
    val address: String,
    val date: String,
    val startTime: String,
    val endTime: String,
    val orderStatus: OrderStatus = OrderStatus.PESAN,
)