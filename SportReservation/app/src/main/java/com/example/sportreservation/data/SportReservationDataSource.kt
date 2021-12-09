package com.example.sportreservation.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.data.source.local.entity.HistoryEntity
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.utils.Resource

interface SportReservationDataSource {

    // Sport Place
    fun getBadmintonPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>>
    fun getBasketPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>>
    fun getFutsalPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>>
    fun getSportById(id: Int): LiveData<SportPlaceEntity>


    fun getArticle(): LiveData<Resource<PagedList<ArticleEntity>>>
    fun getArticleById(id: Int): LiveData<ArticleEntity>

    // History
    fun insertHistory(historyEntity: HistoryEntity)
    fun getHistory(): LiveData<PagedList<HistoryEntity>>

    // Order
    fun insertOrder(order: OrderEntity)
    fun getOrderList(): LiveData<PagedList<OrderEntity>>
    fun deleteOrder(order: OrderEntity)
    fun getOrderByDate(date: String): List<OrderEntity>
    fun getOrderById(id: Int): LiveData<OrderEntity>

}