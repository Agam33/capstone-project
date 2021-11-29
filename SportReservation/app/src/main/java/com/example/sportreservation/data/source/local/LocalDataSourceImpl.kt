package com.example.sportreservation.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.data.source.local.entity.HistoryEntity
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.data.source.local.room.SportReservationDao

class LocalDataSourceImpl(
    private val sportReservationDao: SportReservationDao
): LocalDataSource {

    override fun insertSport(sport: List<SportPlaceEntity>) =
        sportReservationDao.insertSport(sport)

    override fun insertArticles(article: List<ArticleEntity>) =
        sportReservationDao.insertArticles(article)

    override fun getBySportName(sportName: String): DataSource.Factory<Int, SportPlaceEntity> =
        sportReservationDao.getBySportName(sportName)

    override fun getSportById(id: Int): LiveData<SportPlaceEntity> =
        sportReservationDao.getSportById(id)

    override fun getArticleById(id: Int): LiveData<ArticleEntity> =
        sportReservationDao.getArticleById(id)

    override fun getArticles(): DataSource.Factory<Int, ArticleEntity> =
        sportReservationDao.getArticles()

    override fun getOrderList(): DataSource.Factory<Int, OrderEntity> =
        sportReservationDao.getOrderList()

    override fun getHistory(query: SupportSQLiteQuery): DataSource.Factory<Int, HistoryEntity> =
        sportReservationDao.getHistory(query)

    override fun getOrderByDate(date: String): List<OrderEntity> =
        sportReservationDao.getOrderByDate(date)

    override fun deleteOrder(order: OrderEntity) {
        sportReservationDao.deleteOrder(order)
    }
}