package com.musalaSoft.weather.domain.datasource.forecast

import com.musalaSoft.weather.db.dao.IForecastDao
import com.musalaSoft.weather.db.entity.ForecastEntity
import com.musalaSoft.weather.domain.model.response.ForecastResponse
import javax.inject.Inject

/**
 * Created by Emel E. on 2020-09-01
 */


class ForecastLocalDataSource @Inject constructor(private val forecastDao: IForecastDao) {

    fun getForecast() = forecastDao.getForecast()

    fun insertForecast(forecast: ForecastResponse) = forecastDao.deleteAndInsert(ForecastEntity(forecast))
}
