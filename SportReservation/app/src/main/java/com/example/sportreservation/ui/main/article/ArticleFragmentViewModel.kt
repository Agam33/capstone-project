package com.example.sportreservation.ui.main.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.sportreservation.data.SportReservationRepository
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.utils.Resource

class ArticleFragmentViewModel(
    private val sportReservationRepository: SportReservationRepository
): ViewModel() {

    fun getArticle(): LiveData<Resource<PagedList<ArticleEntity>>> =
        sportReservationRepository.getArticle()
}