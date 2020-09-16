package com.musalaSoft.weather.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.musalaSoft.weather.di.ViewModelFactory
import com.musalaSoft.weather.di.key.ViewModelKey
import com.musalaSoft.weather.ui.dashboard.DashboardFragmentViewModel
import com.musalaSoft.weather.ui.dashboard.forecast.ForecastItemViewModel
import com.musalaSoft.weather.ui.main.MainActivityViewModel
import com.musalaSoft.weather.ui.search.SearchViewModel
import com.musalaSoft.weather.ui.search.result.SearchResultItemViewModel
import com.musalaSoft.weather.ui.splash.SplashFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Emel E. on 2020-09-01
 */


@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(SplashFragmentViewModel::class)
    abstract fun provideSplashFragmentViewModel(splashFragmentViewModel: SplashFragmentViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun provideMainViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(DashboardFragmentViewModel::class)
    abstract fun provideDashboardFragmentViewModel(dashboardFragmentViewModel: DashboardFragmentViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(ForecastItemViewModel::class)
    abstract fun provideForecastItemViewModel(forecastItemViewModel: ForecastItemViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SearchViewModel::class)
    abstract fun provideSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SearchResultItemViewModel::class)
    abstract fun provideSearchResultItemViewModel(searchResultItemViewModel: SearchResultItemViewModel): ViewModel
}
