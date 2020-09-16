package com.musalaSoft.weather.di.scope

import javax.inject.Scope
import kotlin.annotation.Retention

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity
