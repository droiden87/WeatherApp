package com.musalaSoft.weather.di.module

import com.musalaSoft.weather.di.scope.PerActivity
import com.musalaSoft.weather.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
/**
 * Created by Emel E. on 2020-09-01
 */


@Module
abstract class ActivityModule {

    /**
     * Injects [MainActivity]
     *
     * @return an instance of [MainActivity]
     */

    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun mainActivity(): MainActivity
}
