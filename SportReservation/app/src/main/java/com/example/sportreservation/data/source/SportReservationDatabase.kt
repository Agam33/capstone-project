package com.example.sportreservation.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sportreservation.data.source.entity.UserEntity


@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SportReservationDatabase:RoomDatabase() {
    abstract fun sportReservationDao(): SportReservationDao
}