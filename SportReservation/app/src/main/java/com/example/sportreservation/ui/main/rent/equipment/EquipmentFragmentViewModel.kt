package com.example.sportreservation.ui.main.rent.equipment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sportreservation.data.SportReservationRepository
import com.example.sportreservation.data.source.remote.response.EquipmentResponse

class EquipmentFragmentViewModel(private val sportReservationRepository: SportReservationRepository): ViewModel() {

    fun getEquipment(): LiveData<List<EquipmentResponse>> = sportReservationRepository.getEquipment()
}