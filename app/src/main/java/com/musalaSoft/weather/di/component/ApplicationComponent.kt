package com.musalaSoft.weather.di.component


import android.app.Application
import com.musalaSoft.weather.WeatherApplication
import com.musalaSoft.weather.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        ActivityModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(weatherApp: WeatherApplication)
}
