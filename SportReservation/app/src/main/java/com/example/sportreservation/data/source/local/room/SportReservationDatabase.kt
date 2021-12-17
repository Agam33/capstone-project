package com.example.sportreservation.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sportreservation.data.source.local.entity.ArticleEntity
import com.example.sportreservation.data.source.local.entity.HistoryEntity
import com.example.sportreservation.data.source.local.entity.OrderEntity
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity


@Database(
    entities = [
        HistoryEntity::class,
        OrderEntity::class,
        ArticleEntity::class,
        SportPlaceEntity::class,

    ],
    version = 1,
    exportSchema = false
)
abstract class SportReservationDatabase : RoomDatabase() {
    abstract fun sportReservationDao(): SportReservationDao
}