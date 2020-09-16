package com.musalaSoft.weather.domain.datasource.searchCities

import androidx.lifecycle.LiveData
import com.musalaSoft.weather.db.dao.ICitiesForSearchDao
import com.musalaSoft.weather.db.entity.CitiesForSearchEntity
import com.musalaSoft.weather.domain.model.response.SearchResponse
import javax.inject.Inject

/**
 * Created by Emel E. on 2020-09-01
 */

class SearchCitiesLocalDataSource @Inject constructor(private val citiesForSearchDao: ICitiesForSearchDao) {

    fun getCityByName(cityName: String?): LiveData<List<CitiesForSearchEntity>> = citiesForSearchDao.getCityByName(cityName)

    fun insertCities(response: SearchResponse) {
        response.hits
            ?.map { CitiesForSearchEntity(it) }
            ?.let { citiesForSearchDao.insertCities(it) }
    }
}
