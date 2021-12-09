package com.example.sportreservation.ui.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.sportreservation.data.SportReservationRepository
import com.example.sportreservation.data.source.local.entity.HistoryEntity

class HistoryFragmentViewModel(
    private val sportReservationRepository: SportReservationRepository
) : ViewModel() {

    fun getHistoryOrder(): LiveData<PagedList<HistoryEntity>> =
        sportReservationRepository.getHistory()

}