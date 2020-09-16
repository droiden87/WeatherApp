package com.musalaSoft.weather.di.module


import android.content.Context
import androidx.room.Room
import com.musalaSoft.weather.db.WeatherDatabase
import com.musalaSoft.weather.db.dao.ICitiesForSearchDao
import com.musalaSoft.weather.db.dao.ICurrentWeatherDao
import com.musalaSoft.weather.db.dao.IForecastDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
/**
 * Created by Emel E. on 2020-09-01
 */

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java, "WeatherApp-DB"
        ).build()
    }

    @Singleton
    @Provides
    fun provideForecastDao(db: WeatherDatabase): IForecastDao {
        return db.forecastDao()
    }

    @Singleton
    @Provides
    fun provideCurrentWeatherDao(db: WeatherDatabase): ICurrentWeatherDao {
        return db.currentWeatherDao()
    }

    @Singleton
    @Provides
    fun provideCitiesForSearchDao(db: WeatherDatabase): ICitiesForSearchDao {
        return db.citiesForSearchDao()
    }
}
