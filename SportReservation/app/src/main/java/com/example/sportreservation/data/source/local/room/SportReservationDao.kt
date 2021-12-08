package com.example.sportreservation.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.data.source.local.entity.HistoryEntity
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity

@Dao

interface SportReservationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSport(sport: List<SportPlaceEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun  insertArticles(articleEntity: List<ArticleEntity>)

    @Insert
    fun insertOrder(order: OrderEntity)

    @Query("SELECT * FROM sportplaceentity WHERE sportName = :sportName")
    fun getBySportName(sportName: String): DataSource.Factory<Int, SportPlaceEntity>

    @Query("SELECT * FROM sportplaceentity WHERE id = :id")
    fun getSportById(id: Int): LiveData<SportPlaceEntity>

    @Query("SELECT * FROM articleentity")
    fun getArticles(): DataSource.Factory<Int, ArticleEntity>

    @Query("SELECT * FROM articleentity WHERE id= :id")
    fun getArticleById(id: Int): LiveData<ArticleEntity>

    @Query("SELECT * FROM orderentity")
    fun getOrderList(): DataSource.Factory<Int, OrderEntity>

    @RawQuery(observedEntities = [HistoryEntity::class])
    fun getHistory(query: SupportSQLiteQuery): DataSource.Factory<Int, HistoryEntity>

    @Query("SELECT * FROM orderentity WHERE date = :date")
    fun getOrderByDate(date: String): List<OrderEntity>

    @Query("SELECT * FROM orderentity WHERE id = :id")
    fun getOrderById(id: Int): LiveData<OrderEntity>

    @Delete
    fun deleteOrder(order: OrderEntity)

}

