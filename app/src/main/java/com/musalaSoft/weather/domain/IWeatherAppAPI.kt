package com.musalaSoft.weather.domain

import com.musalaSoft.weather.domain.model.response.CurrentWeatherResponse
import com.musalaSoft.weather.domain.model.response.ForecastResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherAppAPI {

    @GET("forecast")
    fun getForecastByGeoCords(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("units")
        units: String
    ): Single<ForecastResponse>

    @GET("weather")
    fun getCurrentByGeoCords(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("units")
        units: String
    ): Single<CurrentWeatherResponse>
}
