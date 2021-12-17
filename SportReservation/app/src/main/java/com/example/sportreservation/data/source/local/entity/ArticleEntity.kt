package com.example.sportreservation.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val writer: String,
    val imgUrl: String,
    val content: String,
)