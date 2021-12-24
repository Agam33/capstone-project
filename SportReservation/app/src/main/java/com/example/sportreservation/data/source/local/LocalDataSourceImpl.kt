package com.example.sportreservation.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.data.source.local.entity.HistoryEntity
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.data.source.local.room.SportReservationDao

class LocalDataSourceImpl(
    private val sportReservationDao: SportReservationDao
) : LocalDataSource {

    override fun insertSport(sport: List<SportPlaceEntity>) =
        sportReservationDao.insertSport(sport)

    override fun insertArticles(article: List<ArticleEntity>) =
        sportReservationDao.insertArticles(article)

    override fun insertOrder(order: OrderEntity) =
        sportReservationDao.insertOrder(order)

    override fun insertHistory(historyEntity: HistoryEntity) {
        sportReservationDao.insertHistory(historyEntity)
    }

    override fun insertOrderList(orderList: List<OrderEntity>) =
        sportReservationDao.insertOrderList(orderList)

    override fun getBySportName(sportName: String): DataSource.Factory<Int, SportPlaceEntity> =
        sportReservationDao.getBySportName(sportName)

    override fun getSportById(id: String): LiveData<SportPlaceEntity> =
        sportReservationDao.getSportById(id)

    override fun getArticleById(id: Int): LiveData<ArticleEntity> =
        sportReservationDao.getArticleById(id)

    override fun getArticles(): DataSource.Factory<Int, ArticleEntity> =
        sportReservationDao.getArticles()

    override fun getOrderList(): DataSource.Factory<Int, OrderEntity> =
        sportReservationDao.getOrderList()

    override fun getHistory(): DataSource.Factory<Int, HistoryEntity> =
        sportReservationDao.getHistory()

    override fun getOrderByDate(date: String): List<OrderEntity> =
        sportReservationDao.getOrderByDate(date)

    override fun getOrderById(id: String): LiveData<OrderEntity> =
        sportReservationDao.getOrderById(id)

    override fun deleteOrder(order: OrderEntity) {
        sportReservationDao.deleteOrder(order)
    }
}