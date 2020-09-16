package com.musalaSoft.weather.domain.datasource.currentWeather

import com.musalaSoft.weather.db.dao.ICurrentWeatherDao
import com.musalaSoft.weather.db.entity.CurrentWeatherEntity
import com.musalaSoft.weather.domain.model.response.CurrentWeatherResponse
import javax.inject.Inject

/**
 * Created by Emel E. on 2020-09-01
 */
class CurrentWeatherLocalDataSource @Inject constructor(private val currentWeatherDao: ICurrentWeatherDao) {

    fun getCurrentWeather() = currentWeatherDao.getCurrentWeather()

    fun insertCurrentWeather(currentWeather: CurrentWeatherResponse) = currentWeatherDao.deleteAndInsert(
        CurrentWeatherEntity(currentWeather)
    )
}
