package com.musalaSoft.weather.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.musalaSoft.weather.db.entity.CitiesForSearchEntity
import com.musalaSoft.weather.repo.SearchCitiesRepository
import com.musalaSoft.weather.ui.search.SearchViewState
import com.musalaSoft.weather.utils.UseCaseLiveData
import com.musalaSoft.weather.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Emel E. on 2020-09-01
 */

class SearchCitiesUseCase @Inject internal constructor(private val repository: SearchCitiesRepository) : UseCaseLiveData<SearchViewState, SearchCitiesUseCase.SearchCitiesParams, SearchCitiesRepository>() {

    override fun getRepository(): SearchCitiesRepository = repository

    override fun buildUseCaseObservable(params: SearchCitiesParams?): LiveData<SearchViewState> {
        return repository.loadCitiesByCityName(
            cityName = params?.city ?: ""
        ).map {
            onSearchResultReady(it)
        }
    }

    private fun onSearchResultReady(resource: Resource<List<CitiesForSearchEntity>>): SearchViewState {
        return SearchViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    class SearchCitiesParams(
        val city: String = ""
    ) : Params()
}
