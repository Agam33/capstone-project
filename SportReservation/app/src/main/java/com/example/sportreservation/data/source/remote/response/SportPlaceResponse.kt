package com.example.sportreservation.data.source.remote.response

data class SportPlaceResponse(
    val id: String,
    val name: String,
    val address: String,
    val open: String,
    val close: String,
    val cost: String,
    val phone: String,
    val facility: String,
    val imgUrl: String
)