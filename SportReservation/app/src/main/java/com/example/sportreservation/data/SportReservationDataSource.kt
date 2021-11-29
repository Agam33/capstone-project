package com.example.sportreservation.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.data.source.local.entity.HistoryEntity
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.utils.Resource

interface SportReservationDataSource {
    fun getBadmintonPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>>
    fun getBasketPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>>
    fun getFutsalPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>>
    fun getSportById(id: Int): LiveData<SportPlaceEntity>
    fun getArticle(): LiveData<Resource<PagedList<ArticleEntity>>>
    fun getArticleById(id: Int): LiveData<ArticleEntity>
    fun getOrderList(): LiveData<PagedList<OrderEntity>>
    fun getHistory(query: SupportSQLiteQuery): LiveData<PagedList<HistoryEntity>>
    fun getOrderByDate(date: String): List<OrderEntity>

    fun deleteOrder(order: OrderEntity)
}