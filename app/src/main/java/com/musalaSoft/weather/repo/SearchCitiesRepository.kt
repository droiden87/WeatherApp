package com.musalaSoft.weather.repo

import androidx.lifecycle.LiveData
import com.musalaSoft.weather.core.Constants.NetworkService.RATE_LIMITER_TYPE
import com.musalaSoft.weather.db.entity.CitiesForSearchEntity
import com.musalaSoft.weather.domain.datasource.searchCities.SearchCitiesLocalDataSource
import com.musalaSoft.weather.domain.datasource.searchCities.SearchCitiesRemoteDataSource
import com.musalaSoft.weather.domain.model.response.SearchResponse
import com.musalaSoft.weather.utils.domain.RateLimiter
import com.musalaSoft.weather.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Emel E. on 2020-09-01
 */
class SearchCitiesRepository @Inject constructor(
    private val searchCitiesLocalDataSource: SearchCitiesLocalDataSource,
    private val searchCitiesRemoteDataSource: SearchCitiesRemoteDataSource
) {

    private val rateLimiter = RateLimiter<String>(1, TimeUnit.SECONDS)

    fun loadCitiesByCityName(cityName: String?): LiveData<Resource<List<CitiesForSearchEntity>>> {
        return object : NetworkBoundResource<List<CitiesForSearchEntity>, SearchResponse>() {
            override fun saveCallResult(item: SearchResponse) = searchCitiesLocalDataSource.insertCities(item)

            override fun shouldFetch(data: List<CitiesForSearchEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<CitiesForSearchEntity>> = searchCitiesLocalDataSource.getCityByName(cityName)

            override fun createCall(): Single<SearchResponse> = searchCitiesRemoteDataSource.getCityWithQuery(
                cityName
                    ?: ""
            )

            override fun onFetchFailed() = rateLimiter.reset(RATE_LIMITER_TYPE)
        }.asLiveData
    }
}
