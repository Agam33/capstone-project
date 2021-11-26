package com.example.sportreservation.data.source.remote

import androidx.lifecycle.LiveData
import com.example.sportreservation.data.source.remote.response.SportPlaceResponse

interface RemoteDataSource {

    fun getBadmintonPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>>
    fun getBasketPlace():  LiveData<ApiResponse<List<SportPlaceResponse>>>
    fun getFutsalPlace():  LiveData<ApiResponse<List<SportPlaceResponse>>>

}