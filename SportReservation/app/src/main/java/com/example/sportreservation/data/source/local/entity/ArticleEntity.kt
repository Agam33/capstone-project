package com.example.sportreservation.data.source.local.entity

import androidx.room.Entity

@Entity
data class ArticleEntity(
    val id: Int,
    val writer: String,
    val content: String,
)