package com.musalaSoft.weather.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
/**
 * Created by Emel E. on 2020-09-01
 */

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)
}