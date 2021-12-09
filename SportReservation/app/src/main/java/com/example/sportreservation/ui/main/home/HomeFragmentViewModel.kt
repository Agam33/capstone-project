package com.example.sportreservation.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.sportreservation.data.SportReservationRepository
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.utils.Resource

class HomeFragmentViewModel(
    private val sportReservationRepository: SportReservationRepository
): ViewModel() {

    fun getBadmintonPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>> =
        sportReservationRepository.getBadmintonPlace()

    fun getBasketPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>> =
        sportReservationRepository.getBasketPlace()

    fun getFutsalPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>> =
        sportReservationRepository.getFutsalPlace()

    fun getGolfPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>> =
        sportReservationRepository.getGolfPlace()

}