package com.musalaSoft.weather.ui.dashboard

import com.musalaSoft.weather.core.BaseViewState
import com.musalaSoft.weather.db.entity.CurrentWeatherEntity
import com.musalaSoft.weather.utils.domain.Status

/**
 * Created by Emel E. on 2020-09-01
 */
class CurrentWeatherViewState(
    val status: Status,
    val error: String? = null,
    val data: CurrentWeatherEntity? = null
) : BaseViewState(status, error) {
    fun getForecast() = data
}
