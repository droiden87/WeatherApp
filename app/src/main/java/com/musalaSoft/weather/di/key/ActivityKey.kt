package com.musalaSoft.weather.di.key

import android.app.Activity
import dagger.MapKey
import kotlin.reflect.KClass
/**
 * Created by Emel E. on 2020-09-01
 */

@MapKey
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class ActivityKey(val value: KClass<out Activity>)
