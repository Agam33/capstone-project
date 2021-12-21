package com.example.sportreservation.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.sportreservation.data.SportReservationRepository
import com.example.sportreservation.data.source.local.entity.HistoryEntity
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.utils.Resource

class OrderViewModel(
    private val sportReservationRepository: SportReservationRepository
) : ViewModel() {

    fun getOrderList(): LiveData<Resource<PagedList<OrderEntity>>> =
        sportReservationRepository.getOrderList()

    fun insertHistory(historyEntity: HistoryEntity) {
        sportReservationRepository.insertHistory(historyEntity)
    }

    fun deleteOrder(orderEntity: OrderEntity) {
        sportReservationRepository.deleteOrder(orderEntity)
    }
}