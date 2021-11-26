package com.example.sportreservation.data.source.remote.response

data class SportPlaceResponse(
    val id: Int,
    val name: String,
    val address: String,
    val open: String,
    val close: String,
    val cost: String,
    val phone: String,
    val fasilitas: String,
    val imgUrl: String,
)