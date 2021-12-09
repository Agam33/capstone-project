package com.example.sportreservation.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.sportreservation.data.source.NetworkBoundResource
import com.example.sportreservation.data.source.local.LocalDataSourceImpl
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.data.source.local.entity.HistoryEntity
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.data.source.remote.ApiResponse
import com.example.sportreservation.data.source.remote.RemoteDataSourceImpl
import com.example.sportreservation.data.source.remote.response.ArticleResponse
import com.example.sportreservation.data.source.remote.response.SportPlaceResponse
import com.example.sportreservation.utils.Resource
import com.example.sportreservation.utils.singleThreadIO

class SportReservationRepository(
    private val remoteDataSourceImpl: RemoteDataSourceImpl,
    private val localDataSourceImpl: LocalDataSourceImpl
): SportReservationDataSource {

    companion object {
        const val LOAD_PAGE = 25
        const val PAGE_SIZE = 25
    }

    override fun getBadmintonPlace(): LiveData<Resource<PagedList<SportPlaceEntity>>> {
        val sportName = "badminton"
        return object : NetworkBoundResource<PagedList<SportPlaceEntity>, List<SportPlaceResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<SportPlaceEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(LOAD_PAGE)
                    .setPageSize(PAGE_SIZE)
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
                            0,
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
                    .setInitialLoadSizeHint(LOAD_PAGE)
                    .setPageSize(PAGE_SIZE)
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
                            0,
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
                    .setInitialLoadSizeHint(LOAD_PAGE)
                    .setPageSize(PAGE_SIZE)
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
                          0,
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
        val sportName = "golf"
        return object : NetworkBoundResource<PagedList<SportPlaceEntity>, List<SportPlaceResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<SportPlaceEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(LOAD_PAGE)
                    .setPageSize(PAGE_SIZE)
                    .build()
                return LivePagedListBuilder(localDataSourceImpl.getBySportName(sportName), config).build()
            }

            override fun shouldFetch(data: PagedList<SportPlaceEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<SportPlaceResponse>>> = remoteDataSourceImpl.getGolfPlace()

            override fun saveCallResult(data: List<SportPlaceResponse>) {
                val sportPlace = ArrayList<SportPlaceEntity>()
                for (place in data) {
                    sportPlace.add(
                        SportPlaceEntity(
                            0,
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

    override fun getSportById(id: Int): LiveData<SportPlaceEntity> =
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

    override fun getArticleById(id: Int): LiveData<ArticleEntity> =
        localDataSourceImpl.getArticleById(id)

    override fun getOrderList(): LiveData<PagedList<OrderEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(LOAD_PAGE)
            .setPageSize(PAGE_SIZE)
            .build()
        return LivePagedListBuilder(localDataSourceImpl.getOrderList(), config).build()
    }

    override fun getHistory(query: SupportSQLiteQuery): LiveData<PagedList<HistoryEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(LOAD_PAGE)
            .setPageSize(PAGE_SIZE)
            .build()
        return LivePagedListBuilder(localDataSourceImpl.getHistory(query), config).build()
    }

    override fun insertOrder(order: OrderEntity) =
        singleThreadIO { localDataSourceImpl.insertOrder(order) }

    override fun getOrderByDate(date: String): List<OrderEntity> =
        localDataSourceImpl.getOrderByDate(date)

    override fun getOrderById(id: Int): LiveData<OrderEntity> =
        localDataSourceImpl.getOrderById(id)

    override fun getSportByName(name: String): LiveData<Resource<PagedList<SportPlaceEntity>>> {
        return object : NetworkBoundResource<PagedList<SportPlaceEntity>, List<SportPlaceResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<SportPlaceEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(LOAD_PAGE)
                    .setPageSize(PAGE_SIZE)
                    .build()
                return LivePagedListBuilder(localDataSourceImpl.getBySportName(name), config).build()
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
                            0,
                            place.name,
                            place.address,
                            place.phone,
                            place.open,
                            place.close,
                            place.cost,
                            place.facility,
                            place.imgUrl,
                            name
                        )
                    )
                }
                localDataSourceImpl.insertSport(sportPlace)
            }
        }.asLiveData()
    }



    override fun deleteOrder(order: OrderEntity) =
        singleThreadIO { localDataSourceImpl.deleteOrder(order) }
}
