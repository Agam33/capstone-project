package com.example.sportreservation.data.source.local


import androidx.paging.DataSource
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity


interface LocalDataSource {
    fun getBySportName(sportName: String): DataSource.Factory<Int, SportPlaceEntity>
    fun insertSport(sport:  List<SportPlaceEntity>)
    fun getSportById(id: Int): SportPlaceEntity
    fun insertArticles(article: List<ArticleEntity>)
    fun getArticleById(id: Int): ArticleEntity
    fun getArticles(): DataSource.Factory<Int, ArticleEntity>
}