package com.musalaSoft.weather.domain.datasource.forecast

import com.musalaSoft.weather.domain.IWeatherAppAPI
import com.musalaSoft.weather.domain.model.response.ForecastResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Emel E. on 2020-09-01
 */
class ForecastRemoteDataSource @Inject constructor(private val api: IWeatherAppAPI) {

    fun getForecastByGeoCords(lat: Double, lon: Double, units: String): Single<ForecastResponse> = api.getForecastByGeoCords(lat, lon, units)
}