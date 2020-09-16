package com.musalaSoft.weather.utils
/**
 * Created by Emel E. on 2020-09-01
 */

interface IMapper<R, D> {
    fun mapFrom(type: R): D
}
