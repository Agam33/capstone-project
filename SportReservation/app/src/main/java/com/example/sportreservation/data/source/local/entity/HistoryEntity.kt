package com.example.sportreservation.data.source.local.entity

import androidx.room.Entity

@Entity
data class HistoryEntity(
    val id: Int,
    val name: String,
    val status: Boolean,
    val date: String,
    val imgUrl: String,
)