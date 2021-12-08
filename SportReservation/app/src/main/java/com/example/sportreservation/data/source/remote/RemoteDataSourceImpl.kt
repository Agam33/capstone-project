package com.example.sportreservation.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sportreservation.data.source.remote.response.ArticleResponse
import com.example.sportreservation.data.source.remote.response.SportPlaceResponse
import com.example.sportreservation.utils.JsonHelper
import com.example.sportreservation.utils.mainThreadDelay

class RemoteDataSourceImpl(
    private val jsonHelper: JsonHelper
): RemoteDataSource {

    override fun getBadmintonPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>> {
        val result = MutableLiveData<ApiResponse<List<SportPlaceResponse>>>()
        result.postValue( ApiResponse.success(jsonHelper.loadBadmintonPlace()))
        return result
    }

    override fun getBasketPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>> {
        val result = MutableLiveData<ApiResponse<List<SportPlaceResponse>>>()
        result.postValue(ApiResponse.success(jsonHelper.loadBasketPlace()))
        return result
    }

    override fun getFutsalPlace(): LiveData<ApiResponse<List<SportPlaceResponse>>> {
        val result = MutableLiveData<ApiResponse<List<SportPlaceResponse>>>()
        result.postValue(ApiResponse.success(jsonHelper.loadFutsalPlace()))
        return result
    }

    override fun getArticle(): LiveData<ApiResponse<List<ArticleResponse>>> {
        val result = MutableLiveData<ApiResponse<List<ArticleResponse>>>()
        result.value = ApiResponse.success(jsonHelper.loadArticle())
        return result
    }
}

