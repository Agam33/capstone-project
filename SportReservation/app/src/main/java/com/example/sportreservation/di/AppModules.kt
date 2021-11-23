package com.example.sportreservation.di

import android.app.Application
import androidx.room.Room
import com.example.sportreservation.data.SportReservationRepository
import com.example.sportreservation.data.source.local.room.SportReservationDatabase
import com.example.sportreservation.data.source.local.room.SportReservationDao
import com.example.sportreservation.utils.AppExecutors
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

var appModule = module {
    single { AppExecutors() }
}

var repoModule = module {
    factory { SportReservationRepository( appExecutors = get()) }
}

var viewModels = module {

}

var databaseModule = module {

    fun getDataBaseInstance(application: Application): SportReservationDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            SportReservationDatabase::class.java,
            "SportReservation.db"
        ).build()
    }

    fun provideDao(sportRepositoryDatabase: SportReservationDatabase): SportReservationDao {
        return sportRepositoryDatabase.sportReservationDao()
    }

    single { getDataBaseInstance(application = androidApplication()) }
    single { provideDao(get()) }
}