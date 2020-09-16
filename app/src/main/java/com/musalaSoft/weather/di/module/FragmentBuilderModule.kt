package com.musalaSoft.weather.di.module

import com.musalaSoft.weather.ui.dashboard.DashboardFragment
import com.musalaSoft.weather.ui.search.SearchFragment
import com.musalaSoft.weather.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Emel E. on 2020-09-01
 */

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment
}