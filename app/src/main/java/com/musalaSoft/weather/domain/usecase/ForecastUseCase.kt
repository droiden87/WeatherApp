package com.musalaSoft.weather.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.musalaSoft.weather.core.Constants
import com.musalaSoft.weather.db.entity.ForecastEntity
import com.musalaSoft.weather.repo.ForecastRepository
import com.musalaSoft.weather.ui.dashboard.forecast.ForecastMapper
import com.musalaSoft.weather.ui.dashboard.forecast.ForecastViewState
import com.musalaSoft.weather.utils.UseCaseLiveData
import com.musalaSoft.weather.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Emel E. on 2020-09-01
 */


class ForecastUseCase @Inject internal constructor(private val repository: ForecastRepository) : UseCaseLiveData<ForecastViewState, ForecastUseCase.ForecastParams, ForecastRepository>() {

    override fun getRepository(): ForecastRepository {
        return repository
    }

    override fun buildUseCaseObservable(params: ForecastParams?): LiveData<ForecastViewState> {
        return repository.loadForecastByCoord(
            params?.lat?.toDouble() ?: 0.0,
            params?.lon?.toDouble() ?: 0.0,
            params?.fetchRequired
                ?: false,
            units = params?.units ?: Constants.Coords.METRIC
        ).map {
            onForecastResultReady(it)
        }
    }

    private fun onForecastResultReady(resource: Resource<ForecastEntity>): ForecastViewState {
        val mappedList = resource.data?.list?.let { ForecastMapper().mapFrom(it) }
        resource.data?.list = mappedList

        return ForecastViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    class ForecastParams(
        val lat: String = "",
        val lon: String = "",
        val fetchRequired: Boolean,
        val units: String
    ) : Params()
}
