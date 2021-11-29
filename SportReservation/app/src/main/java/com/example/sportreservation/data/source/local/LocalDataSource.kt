package com.example.sportreservation.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.data.source.local.entity.HistoryEntity
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity

interface LocalDataSource {
    fun insertSport(sport:  List<SportPlaceEntity>)
    fun insertArticles(article: List<ArticleEntity>)

    fun getBySportName(sportName: String): DataSource.Factory<Int, SportPlaceEntity>
    fun getSportById(id: Int): LiveData<SportPlaceEntity>
    fun getArticleById(id: Int): LiveData<ArticleEntity>
    fun getArticles(): DataSource.Factory<Int, ArticleEntity>
    fun getOrderList(): DataSource.Factory<Int, OrderEntity>
    fun getHistory(query: SupportSQLiteQuery):  DataSource.Factory<Int, HistoryEntity>
    fun getOrderByDate(date: String): List<OrderEntity>

    fun deleteOrder(order: OrderEntity)
}
