package com.example.sportreservation.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.sportreservation.data.source.NetworkBoundResource
import com.example.sportreservation.data.source.local.LocalDataSourceImpl
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.data.source.remote.ApiResponse
import com.example.sportreservation.data.source.remote.RemoteDataSourceImpl
import com.example.sportreservation.data.source.remote.response.ArticleResponse
import com.example.sportreservation.data.source.remote.response.SportPlaceResponse
import com.example.sportreservation.utils.Resource

class SportReservationRepository(
    private val remoteDataSourceImpl: RemoteDataSourceImpl,
    private val localDataSourceImpl: LocalDataSourceImpl
): SportReservationDataSource {

    override fun getBadmintonPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>> {
        val sportName = "badminton"
        return object : NetworkBoundResource<PagedList<SportPlaceEntity>, List<SportPlaceResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<SportPlaceEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSourceImpl.getBySportName(sportName), config).build()
            }

            override fun shouldFetch(data: PagedList<SportPlaceEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<SportPlaceResponse>>> =
                remoteDataSourceImpl.getBadmintonPlace()

            override fun saveCallResult(data: List<SportPlaceResponse>) {
                val sportPlace = ArrayList<SportPlaceEntity>()
                for(place in data) {
                    sportPlace.add(
                        SportPlaceEntity(
                            place.id,
                            place.name,
                            place.address,
                            place.phone,
                            place.open,
                            place.close,
                            place.cost,
                            place.facility,
                            place.imgUrl,
                            sportName
                        )
                    )
                }
                localDataSourceImpl.insertSport(sportPlace)
            }
        }.asLiveData()
    }

    override fun getBasketPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>> {
        val sportName = "basket"
        return object : NetworkBoundResource<PagedList<SportPlaceEntity>, List<SportPlaceResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<SportPlaceEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSourceImpl.getBySportName(sportName), config).build()
            }

            override fun shouldFetch(data: PagedList<SportPlaceEntity>?): Boolean  =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<SportPlaceResponse>>> =
                remoteDataSourceImpl.getBasketPlace()

            override fun saveCallResult(data: List<SportPlaceResponse>) {
                val sportPlace = ArrayList<SportPlaceEntity>()
                for(place in data) {
                    sportPlace.add(
                        SportPlaceEntity(
                            place.id,
                            place.name,
                            place.address,
                            place.phone,
                            place.open,
                            place.close,
                            place.cost,
                            place.facility,
                            place.imgUrl,
                            sportName
                        )
                    )
                }
                localDataSourceImpl.insertSport(sportPlace)
            }
        }.asLiveData()
    }

    override fun getFutsalPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>> {
        val sportName = "futsal"
        return object : NetworkBoundResource<PagedList<SportPlaceEntity>, List<SportPlaceResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<SportPlaceEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSourceImpl.getBySportName(sportName), config).build()
            }

            override fun shouldFetch(data: PagedList<SportPlaceEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<SportPlaceResponse>>> =
                remoteDataSourceImpl.getFutsalPlace()

            override fun saveCallResult(data: List<SportPlaceResponse>) {
                val sportPlace = ArrayList<SportPlaceEntity>()
                for (place in data) {
                    sportPlace.add(
                        SportPlaceEntity(
                            place.id,
                            place.name,
                            place.address,
                            place.phone,
                            place.open,
                            place.close,
                            place.cost,
                            place.facility,
                            place.imgUrl,
                            sportName
                        )
                    )
                }
                localDataSourceImpl.insertSport(sportPlace)
            }
        }.asLiveData()
    }

    override fun getSportById(id: Int): SportPlaceEntity =
        localDataSourceImpl.getSportById(id)

    override fun getArticle(): LiveData<Resource<PagedList<ArticleEntity>>> {
        return object : NetworkBoundResource<PagedList<ArticleEntity>, List<ArticleResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<ArticleEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSourceImpl.getArticles(), config).build()
            }

            override fun shouldFetch(data: PagedList<ArticleEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<ArticleResponse>>> =
                remoteDataSourceImpl.getArticle()

            override fun saveCallResult(data: List<ArticleResponse>) {
                val articles = ArrayList<ArticleEntity>()
                for(article in data) {
                    articles.add(
                        ArticleEntity(
                            article.id,
                            article.title,
                            article.writer,
                            article.imgUrl,
                            article.content
                        )
                    )
                }
                localDataSourceImpl.insertArticles(articles)
            }
        }.asLiveData()
    }

    override fun getArticleById(id: Int): ArticleEntity =
        localDataSourceImpl.getArticleById(id)

}
