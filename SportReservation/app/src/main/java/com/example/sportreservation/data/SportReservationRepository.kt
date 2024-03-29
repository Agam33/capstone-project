package com.example.sportreservation.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.sportreservation.data.source.NetworkBoundResource
import com.example.sportreservation.data.source.local.LocalDataSourceImpl
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.data.source.local.entity.HistoryEntity
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.data.source.remote.ApiResponse
import com.example.sportreservation.data.source.remote.RemoteDataSourceImpl
import com.example.sportreservation.data.source.remote.response.*
import com.example.sportreservation.utils.OrderStatus
import com.example.sportreservation.utils.Resource
import com.example.sportreservation.utils.singleThreadIO

class SportReservationRepository(
    private val remoteDataSourceImpl: RemoteDataSourceImpl,
    private val localDataSourceImpl: LocalDataSourceImpl
) : SportReservationDataSource {

    override fun getBadmintonPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>> {
        val sportName = "Badminton"
        return object :
            NetworkBoundResource<PagedList<SportPlaceEntity>, List<SportPlaceResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<SportPlaceEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(LOAD_PAGE)
                    .setPageSize(PAGE_SIZE)
                    .build()
                return LivePagedListBuilder(
                    localDataSourceImpl.getBySportName(sportName),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<SportPlaceEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<SportPlaceResponse>>> =
                remoteDataSourceImpl.getBadmintonPlace()

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

    override fun getBasketPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>> {
        val sportName = "Basket"
        return object :
            NetworkBoundResource<PagedList<SportPlaceEntity>, List<SportPlaceResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<SportPlaceEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(LOAD_PAGE)
                    .setPageSize(PAGE_SIZE)
                    .build()
                return LivePagedListBuilder(
                    localDataSourceImpl.getBySportName(sportName),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<SportPlaceEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<SportPlaceResponse>>> =
                remoteDataSourceImpl.getBasketPlace()

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

    override fun getFutsalPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>> {
        val sportName = "Futsal"
        return object :
            NetworkBoundResource<PagedList<SportPlaceEntity>, List<SportPlaceResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<SportPlaceEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(LOAD_PAGE)
                    .setPageSize(PAGE_SIZE)
                    .build()
                return LivePagedListBuilder(
                    localDataSourceImpl.getBySportName(sportName),
                    config
                ).build()
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

    override fun getGolfPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>> {
        val sportName = "Golf"
        return object :
            NetworkBoundResource<PagedList<SportPlaceEntity>, List<SportPlaceResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<SportPlaceEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(LOAD_PAGE)
                    .setPageSize(PAGE_SIZE)
                    .build()
                return LivePagedListBuilder(
                    localDataSourceImpl.getBySportName(sportName),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<SportPlaceEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<SportPlaceResponse>>> =
                remoteDataSourceImpl.getGolfPlace()

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

    override fun getFootballPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>> {
        val sportName = "Football"
        return object :
            NetworkBoundResource<PagedList<SportPlaceEntity>, List<SportPlaceResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<SportPlaceEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(LOAD_PAGE)
                    .setPageSize(PAGE_SIZE)
                    .build()
                return LivePagedListBuilder(
                    localDataSourceImpl.getBySportName(sportName),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<SportPlaceEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<SportPlaceResponse>>> =
                remoteDataSourceImpl.getFootballPlace()

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

    override fun getSportById(id: String): LiveData<SportPlaceEntity> =
        localDataSourceImpl.getSportById(id)

    override fun getArticle(): LiveData<Resource<PagedList<ArticleEntity>>> {
        return object : NetworkBoundResource<PagedList<ArticleEntity>, List<ArticleResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<ArticleEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(LOAD_PAGE)
                    .setPageSize(PAGE_SIZE)
                    .build()
                return LivePagedListBuilder(localDataSourceImpl.getArticles(), config).build()
            }

            override fun shouldFetch(data: PagedList<ArticleEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ArticleResponse>>> =
                remoteDataSourceImpl.getArticle()

            override fun saveCallResult(data: List<ArticleResponse>) {
                val articles = ArrayList<ArticleEntity>()
                for (article in data) {
                    articles.add(
                        ArticleEntity(
                            0,
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

    override fun getArticleById(id: Int): LiveData<ArticleEntity> =
        localDataSourceImpl.getArticleById(id)

    override fun getReferee(): LiveData<List<RefereeResponse>> {
        return remoteDataSourceImpl.getReferee()
    }

    override fun getEquipment(): LiveData<List<EquipmentResponse>> {
        return remoteDataSourceImpl.getEquipment()
    }

    override fun insertHistory(historyEntity: HistoryEntity) {
        singleThreadIO { localDataSourceImpl.insertHistory(historyEntity) }
    }

    override fun getOrderList(): LiveData<Resource<PagedList<OrderEntity>>> {
        return object : NetworkBoundResource<PagedList<OrderEntity>, List<OrderResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<OrderEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(LOAD_PAGE)
                    .setPageSize(PAGE_SIZE)
                    .build()
                return LivePagedListBuilder(localDataSourceImpl.getOrderList(), config).build()
            }

            override fun shouldFetch(data: PagedList<OrderEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<OrderResponse>>> =
                remoteDataSourceImpl.getOrderList()

            override fun saveCallResult(data: List<OrderResponse>) {
                val orderList = ArrayList<OrderEntity>()
                Log.d(TAG, "saveCallResult : $data")
                data.forEach {
                    orderList.add(
                        OrderEntity(
                            it.placeId,
                            it.placeName,
                            it.sportName,
                            it.address,
                            it.date,
                            it.startTime,
                            it.endTime,
                            OrderStatus.PESAN,
                    ))
                }
                localDataSourceImpl.insertOrderList(orderList)
            }
        }.asLiveData()

    }

    override fun getHistory(): LiveData<PagedList<HistoryEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(LOAD_PAGE)
            .setPageSize(PAGE_SIZE)
            .build()
        return LivePagedListBuilder(localDataSourceImpl.getHistory(), config).build()
    }

    override fun insertOrder(order: OrderEntity) =
        singleThreadIO { localDataSourceImpl.insertOrder(order) }

    override fun getOrderByDate(date: String): List<OrderEntity> =
        localDataSourceImpl.getOrderByDate(date)

    override fun getOrderById(id: String): LiveData<OrderEntity> =
        localDataSourceImpl.getOrderById(id)

    override fun deleteAllOrder() = singleThreadIO { localDataSourceImpl.deleteAllOrder() }

    override fun deleteOrder(order: OrderEntity) =
        singleThreadIO { localDataSourceImpl.deleteOrder(order) }

    companion object {
        const val LOAD_PAGE = 25
        const val PAGE_SIZE = 25
    }
}