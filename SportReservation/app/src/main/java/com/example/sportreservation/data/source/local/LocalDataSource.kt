package com.example.sportreservation.data.source.local


import androidx.paging.DataSource
import com.example.sportreservation.data.source.local.entity.SportPlaceEntity


interface LocalDataSource {
    fun getBySportName(sportName: String): DataSource.Factory<Int, SportPlaceEntity>
    fun insertSport(sport:  List<SportPlaceEntity>)
}