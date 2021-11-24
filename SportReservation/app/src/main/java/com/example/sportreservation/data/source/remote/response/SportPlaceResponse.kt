package com.example.sportreservation.data.source.remote.response

data class SportPlaceResponse(
    val id: Int,
    val name: String,
    val address: String,
    val phone: String,
    val open: String,
    val cost: Long,
    val imgUrl: String,
)