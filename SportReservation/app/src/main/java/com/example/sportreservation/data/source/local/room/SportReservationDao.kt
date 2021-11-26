package com.example.sportreservation.data.source.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity

@Dao
interface SportReservationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSport(sport: List<SportPlaceEntity>)

    @Query("SELECT * FROM sportplaceentity WHERE sportName = :sportName")
    fun getBySportName(sportName: String): DataSource.Factory<Int, SportPlaceEntity>

}