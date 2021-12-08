package com.example.sportreservation.ui.order.input

import androidx.lifecycle.LiveData
import com.example.sportreservation.data.SportReservationRepository
import com.example.sportreservation.data.source.local.entity.OrderEntity

class OrderInputViewModel(
    private val sportReservationRepository: SportReservationRepository
) {

    fun insertOrder(order: OrderEntity) =
        sportReservationRepository.insertOrder(order)
}