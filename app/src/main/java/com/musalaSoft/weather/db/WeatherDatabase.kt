package com.musalaSoft.weather.db
/**
 * Created by Emel E. on 2020-09-01
 */


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.musalaSoft.weather.db.dao.ICitiesForSearchDao
import com.musalaSoft.weather.db.dao.ICurrentWeatherDao
import com.musalaSoft.weather.db.dao.IForecastDao
import com.musalaSoft.weather.db.entity.CitiesForSearchEntity
import com.musalaSoft.weather.db.entity.CurrentWeatherEntity
import com.musalaSoft.weather.db.entity.ForecastEntity
import com.musalaSoft.weather.utils.typeconverters.DataConverter

@Database(
    entities = [
        ForecastEntity::class,
        CurrentWeatherEntity::class,
        CitiesForSearchEntity::class
    ],
    version = 2,
    exportSchema = false

)
@TypeConverters(DataConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun forecastDao(): IForecastDao

    abstract fun currentWeatherDao(): ICurrentWeatherDao

    abstract fun citiesForSearchDao(): ICitiesForSearchDao
}
