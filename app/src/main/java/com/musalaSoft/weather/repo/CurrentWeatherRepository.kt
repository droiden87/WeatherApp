package com.musalaSoft.weather.repo

import androidx.lifecycle.LiveData
import com.musalaSoft.weather.core.Constants.NetworkService.RATE_LIMITER_TYPE
import com.musalaSoft.weather.db.entity.CurrentWeatherEntity
import com.musalaSoft.weather.domain.datasource.currentWeather.CurrentWeatherLocalDataSource
import com.musalaSoft.weather.domain.datasource.currentWeather.CurrentWeatherRemoteDataSource
import com.musalaSoft.weather.domain.model.response.CurrentWeatherResponse
import com.musalaSoft.weather.utils.domain.RateLimiter
import com.musalaSoft.weather.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Emel E. on 2020-09-01
 */
class CurrentWeatherRepository @Inject constructor(
    private val currentWeatherRemoteDataSource: CurrentWeatherRemoteDataSource,
    private val currentWeatherLocalDataSource: CurrentWeatherLocalDataSource
) {

    private val currentWeatherRateLimit = RateLimiter<String>(30, TimeUnit.SECONDS)

    fun loadCurrentWeatherByGeoCords(lat: Double, lon: Double, fetchRequired: Boolean, units: String): LiveData<Resource<CurrentWeatherEntity>> {
        return object : NetworkBoundResource<CurrentWeatherEntity, CurrentWeatherResponse>() {
            override fun saveCallResult(item: CurrentWeatherResponse) = currentWeatherLocalDataSource.insertCurrentWeather(item)

            override fun shouldFetch(data: CurrentWeatherEntity?): Boolean = fetchRequired

            override fun loadFromDb(): LiveData<CurrentWeatherEntity> = currentWeatherLocalDataSource.getCurrentWeather()

            override fun createCall(): Single<CurrentWeatherResponse> = currentWeatherRemoteDataSource.getCurrentWeatherByGeoCords(lat, lon, units)

            override fun onFetchFailed() = currentWeatherRateLimit.reset(RATE_LIMITER_TYPE)
        }.asLiveData
    }
}
