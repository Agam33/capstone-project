package com.example.sportreservation.ui.main.rent.referee

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sportreservation.data.SportReservationRepository
import com.example.sportreservation.data.source.remote.response.RefereeResponse

class RefereeFragmentViewModel(
    private val sportReservationRepository: SportReservationRepository
) : ViewModel() {

    fun getReferee(): LiveData<List<RefereeResponse>> =
        sportReservationRepository.getReferee()
}