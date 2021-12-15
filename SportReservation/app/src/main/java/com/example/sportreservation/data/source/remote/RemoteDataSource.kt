package com.example.sportreservation.data.source.remote

import androidx.lifecycle.LiveData
import com.example.sportreservation.data.source.remote.response.ArticleResponse
import com.example.sportreservation.data.source.remote.response.EquipmentResponse
import com.example.sportreservation.data.source.remote.response.RefereeResponse
import com.example.sportreservation.data.source.remote.response.SportPlaceResponse

interface RemoteDataSource {

    fun getBadmintonPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>>
    fun getBasketPlace():  LiveData<ApiResponse<List<SportPlaceResponse>>>
    fun getFutsalPlace():  LiveData<ApiResponse<List<SportPlaceResponse>>>
    fun getGolfPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>>
    fun getFootballPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>>
    fun getArticle():  LiveData<ApiResponse<List<ArticleResponse>>>
    fun getReferee(): LiveData<List<RefereeResponse>>
    fun getEquipment(): LiveData<List<EquipmentResponse>>
}
