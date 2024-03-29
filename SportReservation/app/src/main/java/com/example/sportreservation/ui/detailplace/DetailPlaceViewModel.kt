package com.example.sportreservation.ui.detailplace

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.PagedList
import com.example.sportreservation.data.SportReservationRepository
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.utils.Resource

class DetailPlaceViewModel(
    private val sportReservationRepository: SportReservationRepository
) : ViewModel() {

    private val _detailId = MutableLiveData<String>()
    private val _detailPlace = _detailId.switchMap { id ->
        sportReservationRepository.getSportById(id)
    }

    val detailPlace: LiveData<SportPlaceEntity> = _detailPlace

    fun getById(id: String) {
        _detailId.value = id
    }

    fun getOrderById(id: String): LiveData<OrderEntity> =
        sportReservationRepository.getOrderById(id)

    fun getOrderList(): LiveData<Resource<PagedList<OrderEntity>>> =
        sportReservationRepository.getOrderList()

}