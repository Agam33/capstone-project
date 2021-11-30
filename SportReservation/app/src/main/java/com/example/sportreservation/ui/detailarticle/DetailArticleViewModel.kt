package com.example.sportreservation.ui.detailarticle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.sportreservation.data.SportReservationRepository
import com.example.sportreservation.data.source.local.entity.ArticleEntity

class DetailArticleViewModel(
    private val sportReservationRepository: SportReservationRepository
): ViewModel() {
    
    private var _detailId = MutableLiveData<Int>()
    private val _detailArticle = _detailId.switchMap { id ->
        sportReservationRepository.getArticleById(id)
    }

    val detailArticle: LiveData<ArticleEntity> = _detailArticle

    fun getById(id: Int) {
        _detailId.value = id
    }
}