package com.musalaSoft.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.musalaSoft.weather.db.entity.CurrentWeatherEntity


/**
 * Created by Emel E. on 2020-09-01
 */

@Dao
interface ICurrentWeatherDao {

    @Query("SELECT * FROM CurrentWeather")
    fun getCurrentWeather(): LiveData<CurrentWeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(currentWeatherEntity: CurrentWeatherEntity)

    @Transaction
    fun deleteAndInsert(currentWeatherEntity: CurrentWeatherEntity) {
        deleteCurrentWeather()
        insertCurrentWeather(currentWeatherEntity)
    }

    @Query("DELETE FROM CurrentWeather")
    fun deleteCurrentWeather()

    @Query("Select count(*) from CurrentWeather")
    fun getCount(): Int
}