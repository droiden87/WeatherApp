package com.musalaSoft.weather.ui.dashboard


import android.transition.TransitionInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.musalaSoft.weather.R
import com.musalaSoft.weather.core.BaseFragment
import com.musalaSoft.weather.core.Constants
import com.musalaSoft.weather.databinding.FragmentDashboardBinding
import com.musalaSoft.weather.di.Injectable
import com.musalaSoft.weather.domain.model.ListItem
import com.musalaSoft.weather.domain.usecase.CurrentWeatherUseCase
import com.musalaSoft.weather.domain.usecase.ForecastUseCase
import com.musalaSoft.weather.ui.dashboard.forecast.ForecastAdapter
import com.musalaSoft.weather.ui.main.MainActivity
import com.musalaSoft.weather.utils.LocationHelper
import com.musalaSoft.weather.utils.extensions.isNetworkAvailable
import com.musalaSoft.weather.utils.extensions.observeWith


class DashboardFragment : BaseFragment<DashboardFragmentViewModel, FragmentDashboardBinding>(
    R.layout.fragment_dashboard,
    DashboardFragmentViewModel::class.java
),
    Injectable {

    override fun init() {
        super.init()
        initForecastAdapter()
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        val lat: String? = binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LAT, "")
        val lon: String? = binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LON, "")

        setCurrentWeatherParams(lat!!, lon!!)
        setForecastParams(lat, lon)
        getCurrentWeatherState()
        getForecastState()

    }

    private fun setCurrentWeatherParams(latitude: String, longitude: String) {
        binding.viewModel?.setCurrentWeatherParams(
            CurrentWeatherUseCase.CurrentWeatherParams(
                latitude, longitude, isNetworkAvailable(
                    requireContext()
                ), Constants.Coords.METRIC
            )
        )
    }

    private fun setForecastParams(latitude: String, longitude: String) {
        binding.viewModel?.setForecastParams(
            ForecastUseCase.ForecastParams(
                latitude, longitude, isNetworkAvailable(
                    requireContext()
                ), Constants.Coords.METRIC
            )
        )
    }

    private fun getForecastState() {
        binding.viewModel?.getForecastViewState()?.observeWith(
            viewLifecycleOwner
        ) {
            with(binding) {
                viewState = it
                it.data?.list?.let { forecasts -> initForecast(forecasts) }
                (activity as MainActivity).viewModel.toolbarTitle.set(it.data?.city?.getCityAndCountry())
            }
        }
    }

    private fun getCurrentWeatherState() {
        binding.viewModel?.getCurrentWeatherViewState()?.observeWith(
            viewLifecycleOwner
        ) {
            with(binding) {
                containerForecast.viewState = it
            }
        }
    }

    private fun initForecastAdapter() {
        val adapter = ForecastAdapter { item, cardView, forecastIcon, dayOfWeek, temp, tempMaxMin ->
        }

        binding.recyclerForecast.adapter = adapter
        binding.recyclerForecast.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        postponeEnterTransition()
        binding.recyclerForecast.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
    }

    private fun initForecast(list: List<ListItem>) {
        (binding.recyclerForecast.adapter as ForecastAdapter).submitList(list)
    }

}
