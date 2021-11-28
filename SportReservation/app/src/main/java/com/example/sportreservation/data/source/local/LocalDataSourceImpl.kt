package com.example.sportreservation.data.source.local

import androidx.paging.DataSource
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.data.source.local.room.SportReservationDao

class LocalDataSourceImpl(
    private val sportReservationDao: SportReservationDao
): LocalDataSource {

    override fun getBySportName(sportName: String): DataSource.Factory<Int, SportPlaceEntity> =
        sportReservationDao.getBySportName(sportName)

    override fun insertSport(sport: List<SportPlaceEntity>) =
        sportReservationDao.insertSport(sport)

    override fun getSportById(id: Int): SportPlaceEntity =
        sportReservationDao.getSportById(id)

    override fun insertArticles(article: List<ArticleEntity>) =
        sportReservationDao.insertArticles(article)

    override fun getArticleById(id: Int): ArticleEntity =
        sportReservationDao.getArticleById(id)

    override fun getArticles(): DataSource.Factory<Int, ArticleEntity> =
        sportReservationDao.getArticles()

}