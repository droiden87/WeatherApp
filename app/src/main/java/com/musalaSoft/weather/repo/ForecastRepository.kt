package com.musalaSoft.weather.repo

import androidx.lifecycle.LiveData
import com.musalaSoft.weather.core.Constants.NetworkService.RATE_LIMITER_TYPE
import com.musalaSoft.weather.db.entity.ForecastEntity
import com.musalaSoft.weather.domain.datasource.forecast.ForecastLocalDataSource
import com.musalaSoft.weather.domain.datasource.forecast.ForecastRemoteDataSource
import com.musalaSoft.weather.domain.model.response.ForecastResponse
import com.musalaSoft.weather.utils.domain.RateLimiter
import com.musalaSoft.weather.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Emel E. on 2020-09-01
 */

class ForecastRepository @Inject constructor(
    private val forecastRemoteDataSource: ForecastRemoteDataSource,
    private val forecastLocalDataSource: ForecastLocalDataSource
) {

    private val forecastListRateLimit = RateLimiter<String>(30, TimeUnit.SECONDS)

    fun loadForecastByCoord(lat: Double, lon: Double, fetchRequired: Boolean, units: String): LiveData<Resource<ForecastEntity>> {
        return object : NetworkBoundResource<ForecastEntity, ForecastResponse>() {
            override fun saveCallResult(item: ForecastResponse) = forecastLocalDataSource.insertForecast(item)

            override fun shouldFetch(data: ForecastEntity?): Boolean = fetchRequired

            override fun loadFromDb(): LiveData<ForecastEntity> = forecastLocalDataSource.getForecast()

            override fun createCall(): Single<ForecastResponse> = forecastRemoteDataSource.getForecastByGeoCords(lat, lon, units)

            override fun onFetchFailed() = forecastListRateLimit.reset(RATE_LIMITER_TYPE)
        }.asLiveData
    }
}
