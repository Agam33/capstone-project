package com.example.sportreservation.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.sportreservation.data.SportReservationRepository
import com.example.sportreservation.data.source.local.entity.OrderEntity

class OrderViewModel(
    private val sportReservationRepository: SportReservationRepository
): ViewModel() {

    fun getOrderList(): LiveData<PagedList<OrderEntity>> =
        sportReservationRepository.getOrderList()
}