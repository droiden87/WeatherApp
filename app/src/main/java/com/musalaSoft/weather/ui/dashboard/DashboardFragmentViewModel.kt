package com.musalaSoft.weather.ui.dashboard

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.musalaSoft.weather.core.BaseViewModel
import com.musalaSoft.weather.core.Constants
import com.musalaSoft.weather.db.entity.CoordEntity
import com.musalaSoft.weather.domain.usecase.CurrentWeatherUseCase
import com.musalaSoft.weather.domain.usecase.ForecastUseCase
import com.musalaSoft.weather.ui.dashboard.forecast.ForecastViewState
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Emel E. on 2020-09-01
 */
class DashboardFragmentViewModel @Inject internal constructor(private val forecastUseCase: ForecastUseCase, private val currentWeatherUseCase: CurrentWeatherUseCase, var sharedPreferences: SharedPreferences) : BaseViewModel() {

    private val _forecastParams: MutableLiveData<ForecastUseCase.ForecastParams> = MutableLiveData()
    private val _currentWeatherParams: MutableLiveData<CurrentWeatherUseCase.CurrentWeatherParams> = MutableLiveData()

    fun getForecastViewState() = forecastViewState
    fun getCurrentWeatherViewState() = currentWeatherViewState

    private val forecastViewState: LiveData<ForecastViewState> = _forecastParams.switchMap { params ->
        forecastUseCase.execute(params)
    }
    private val currentWeatherViewState: LiveData<CurrentWeatherViewState> = _currentWeatherParams.switchMap { params ->
        currentWeatherUseCase.execute(params)
    }

    fun setForecastParams(params: ForecastUseCase.ForecastParams) {
        if (_forecastParams.value == params)
            return
        _forecastParams.postValue(params)
    }

    fun setCurrentWeatherParams(params: CurrentWeatherUseCase.CurrentWeatherParams) {
        if (_currentWeatherParams.value == params)
            return
        _currentWeatherParams.postValue(params)
    }
}
