package com.example.sportreservation.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.utils.Resource

interface SportReservationDataSource {
    fun getBadmintonPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>>
    fun getBasketPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>>
    fun getFutsalPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>>
    fun getSportById(id: Int): SportPlaceEntity
    fun getArticle(): LiveData<Resource<PagedList<ArticleEntity>>>
    fun getArticleById(id: Int): ArticleEntity
}