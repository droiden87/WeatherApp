package com.musalaSoft.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.musalaSoft.weather.db.entity.ForecastEntity

/**
 * Created by Emel E. on 2020-09-01
 */

@Dao
interface IForecastDao {

    @Query("SELECT * FROM Forecast")
    fun getForecast(): LiveData<ForecastEntity>

    @Query("SELECT * FROM Forecast ORDER BY abs(lat-:lat) AND abs(lon-:lon) LIMIT 1")
    fun getForecastByCoord(lat: Double, lon: Double): LiveData<ForecastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecast(forecast: ForecastEntity)

    @Transaction
    fun deleteAndInsert(forecast: ForecastEntity) {
        deleteAll()
        insertForecast(forecast)
    }

    @Update
    fun updateForecast(forecast: ForecastEntity)

    @Delete
    fun deleteForecast(forecast: ForecastEntity)

    @Query("DELETE FROM Forecast")
    fun deleteAll()

    @Query("Select count(*) from Forecast")
    fun getCount(): Int

}