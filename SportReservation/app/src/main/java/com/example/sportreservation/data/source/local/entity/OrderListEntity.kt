package com.example.sportreservation.data.source.local.entity

import androidx.room.Entity
import com.example.sportreservation.utils.OrderStatus


@Entity
data class OrderListEntity(
    val id: Int,
    val name: String,
    val dueDate: Long,
    val orderStatus: OrderStatus,
    val imgUrl: String,
)