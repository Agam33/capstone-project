package com.example.sportreservation.ui.detailplace

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.sportreservation.data.SportReservationRepository
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity

class DetailPlaceViewModel(
    private val sportReservationRepository: SportReservationRepository
) : ViewModel() {

    private val _detailId = MutableLiveData<Int>()
    private val _detailPlace = _detailId.switchMap { id ->
        sportReservationRepository.getSportById(id)
    }

    val detailPlace: LiveData<SportPlaceEntity> = _detailPlace

    fun getById(id: Int) {
        _detailId.value = id
    }

    fun getOrderById(id: Int): LiveData<OrderEntity> =
        sportReservationRepository.getOrderById(id)

    fun insertOrder(order: OrderEntity) =
        sportReservationRepository.insertOrder(order)


    fun deleteOrder(orderEntity: OrderEntity) =
        sportReservationRepository.deleteOrder(orderEntity)
}