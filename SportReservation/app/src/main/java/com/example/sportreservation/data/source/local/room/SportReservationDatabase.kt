package com.example.sportreservation.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sportreservation.data.source.local.entity.*


@Database(
    entities = [
        HistoryEntity::class,
        OrderListEntity::class,
        ArticleEntity::class,
        SportPlaceEntity::class,
        RefereeEntity::class,
   ],
    version = 1,
    exportSchema = false
)
abstract class SportReservationDatabase:RoomDatabase() {
    abstract fun sportReservationDao(): SportReservationDao
}