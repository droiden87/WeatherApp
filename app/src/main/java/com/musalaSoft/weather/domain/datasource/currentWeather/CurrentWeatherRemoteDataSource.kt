package com.musalaSoft.weather.domain.datasource.currentWeather

import com.musalaSoft.weather.domain.IWeatherAppAPI
import com.musalaSoft.weather.domain.model.response.CurrentWeatherResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Emel E. on 2020-09-01
 */

class CurrentWeatherRemoteDataSource @Inject constructor(private val api: IWeatherAppAPI) {

    fun getCurrentWeatherByGeoCords(lat: Double, lon: Double, units: String): Single<CurrentWeatherResponse> = api.getCurrentByGeoCords(lat, lon, units)
}