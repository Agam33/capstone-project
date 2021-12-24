package com.example.sportreservation.data.source.remote.response

data class ArticleResponse(
    val id: Int,
    val title: String,
    val writer: String,
    val imgUrl: String,
    val content: String
)