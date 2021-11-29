package com.example.sportreservation.data.source.local

import com.example.sportreservation.data.source.local.room.SportReservationDao

class LocalDataSourceImpl(
    private val sportReservationDao: SportReservationDao
) : LocalDataSource