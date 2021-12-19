package com.example.sportreservation.data.source.remote

import androidx.lifecycle.LiveData
import com.example.sportreservation.data.source.remote.response.*

interface RemoteDataSource {

    fun getBadmintonPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>>
    fun getBasketPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>>
    fun getFutsalPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>>
    fun getGolfPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>>
    fun getFootballPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>>
    fun getArticle(): LiveData<ApiResponse<List<ArticleResponse>>>
    fun getReferee(): LiveData<List<RefereeResponse>>
    fun getEquipment(): LiveData<List<EquipmentResponse>>
    fun getOrderList(): LiveData<ApiResponse<List<OrderResponse>>>
}
