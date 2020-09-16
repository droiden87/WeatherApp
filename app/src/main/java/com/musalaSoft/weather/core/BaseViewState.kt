package com.musalaSoft.weather.core
import com.musalaSoft.weather.utils.domain.Status

/**
 * Created by Emel E. on 2020-09-01
 */


open class BaseViewState(val baseStatus: Status, val baseError: String?) {
    fun isLoading() = baseStatus == Status.LOADING
    fun getErrorMessage() = baseError
    fun shouldShowErrorMessage() = baseError != null
}
