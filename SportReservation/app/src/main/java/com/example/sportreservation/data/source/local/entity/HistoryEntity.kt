package com.example.sportreservation.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportreservation.utils.OrderStatus

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val status: OrderStatus,
    val date: String
)