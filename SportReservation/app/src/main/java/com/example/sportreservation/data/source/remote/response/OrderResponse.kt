package com.example.sportreservation.data.source.remote.response

data class OrderResponse(
    var userId: String = "",
    var placeId: String = "",
    var placeName: String = "",
    var sportName: String = "",
    var address: String = "",
    var startTime: String = "",
    var endTime: String = "",
    var orderStatus: String = "",
    var date: String = ""
)
