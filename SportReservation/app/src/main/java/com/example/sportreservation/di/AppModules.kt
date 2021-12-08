package com.example.sportreservation.di

import android.app.Application
import androidx.room.Room
import com.example.sportreservation.data.SportReservationRepository
import com.example.sportreservation.data.source.local.LocalDataSourceImpl
import com.example.sportreservation.data.source.local.room.SportReservationDao
import com.example.sportreservation.data.source.local.room.SportReservationDatabase
import com.example.sportreservation.data.source.remote.RemoteDataSourceImpl
import com.example.sportreservation.ui.detailarticle.DetailArticleViewModel
import com.example.sportreservation.ui.detailplace.DetailPlaceViewModel
import com.example.sportreservation.ui.main.article.ArticleFragmentViewModel
import com.example.sportreservation.ui.main.home.HomeFragmentViewModel
import com.example.sportreservation.ui.order.OrderViewModel
import com.example.sportreservation.ui.order.input.OrderInputViewModel
import com.example.sportreservation.utils.JsonHelper
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appModule = module {
    single { RemoteDataSourceImpl(jsonHelper = get()) }
    single { LocalDataSourceImpl(sportReservationDao = get()) }
    single { JsonHelper(context = androidContext()) }
}

var repoModule = module {
    factory {
        SportReservationRepository(
            remoteDataSourceImpl = get(),
            localDataSourceImpl = get()
        )
    }
}

var viewModels = module {
    viewModel { HomeFragmentViewModel(sportReservationRepository = get()) }
    viewModel { DetailArticleViewModel(sportReservationRepository = get()) }
    viewModel { DetailPlaceViewModel(sportReservationRepository = get()) }
    viewModel { ArticleFragmentViewModel(sportReservationRepository = get()) }
    viewModel { OrderViewModel(sportReservationRepository = get()) }
    viewModel { OrderInputViewModel(sportReservationRepository = get()) }
}

var databaseModule = module {

    fun getDataBaseInstance(application: Application): SportReservationDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            SportReservationDatabase::class.java,
            "SportReservation.db"
        )

            .build()
    }

    fun provideDao(sportRepositoryDatabase: SportReservationDatabase): SportReservationDao {
        return sportRepositoryDatabase.sportReservationDao()
    }

    single { getDataBaseInstance(application = androidApplication()) }
    single { provideDao(get()) }
}