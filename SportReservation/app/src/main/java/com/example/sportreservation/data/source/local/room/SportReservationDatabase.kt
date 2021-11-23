package com.example.sportreservation.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sportreservation.data.source.local.entity.HistoryEntity
import com.example.sportreservation.data.source.local.entity.OrderListEntity
import com.example.sportreservation.data.source.local.entity.UserEntity


@Database(
    entities = [UserEntity::class, HistoryEntity::class, OrderListEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SportReservationDatabase:RoomDatabase() {
    abstract fun sportReservationDao(): SportReservationDao
}