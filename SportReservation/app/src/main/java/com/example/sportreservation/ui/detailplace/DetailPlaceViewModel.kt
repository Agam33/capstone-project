package com.example.sportreservation.ui.detailplace

import androidx.lifecycle.ViewModel
import com.example.sportreservation.data.SportReservationRepository
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
}