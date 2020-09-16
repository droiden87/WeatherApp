package com.musalaSoft.weather.ui.dashboard.forecast

import com.musalaSoft.weather.core.BaseViewState
import com.musalaSoft.weather.db.entity.ForecastEntity
import com.musalaSoft.weather.utils.domain.Status

/**
 * Created by Emel E. on 2020-09-01
 */

class ForecastViewState(
    val status: Status,
    val error: String? = null,
    val data: ForecastEntity? = null
) : BaseViewState(status, error) {
    fun getForecast() = data
}
