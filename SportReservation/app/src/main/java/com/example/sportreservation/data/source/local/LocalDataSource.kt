package com.example.sportreservation.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.data.source.local.entity.HistoryEntity
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity

interface LocalDataSource {
    fun insertSport(sport: List<SportPlaceEntity>)
    fun insertArticles(article: List<ArticleEntity>)
    fun insertOrder(order: OrderEntity)
    fun insertHistory(historyEntity: HistoryEntity)

    fun getBySportName(sportName: String): DataSource.Factory<Int, SportPlaceEntity>
    fun getSportById(id: Int): LiveData<SportPlaceEntity>
    fun getArticleById(id: Int): LiveData<ArticleEntity>
    fun getArticles(): DataSource.Factory<Int, ArticleEntity>
    fun getOrderList(): DataSource.Factory<Int, OrderEntity>
    fun getHistory(): DataSource.Factory<Int, HistoryEntity>
    fun getOrderByDate(date: String): List<OrderEntity>
    fun getOrderById(id: Int): LiveData<OrderEntity>

    fun deleteOrder(order: OrderEntity)
}
