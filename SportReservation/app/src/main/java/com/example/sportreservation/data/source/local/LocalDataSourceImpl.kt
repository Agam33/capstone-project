package com.example.sportreservation.data.source.local

import androidx.paging.DataSource
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity
import com.example.sportreservation.data.source.local.room.SportReservationDao

class LocalDataSourceImpl(
    private val sportReservationDao: SportReservationDao
): LocalDataSource {

    override fun getBySportName(sportName: String): DataSource.Factory<Int, SportPlaceEntity> =
        sportReservationDao.getBySportName(sportName)

    override fun insertSport(sport: List<SportPlaceEntity>) =
        sportReservationDao.insertSport(sport)

}