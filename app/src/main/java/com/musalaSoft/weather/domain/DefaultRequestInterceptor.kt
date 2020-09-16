package com.musalaSoft.weather.domain

import com.musalaSoft.weather.core.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Emel E. on 2020-09-01
 */

@Singleton
class DefaultRequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
            .newBuilder()
            .addQueryParameter(Constants.NetworkService.API_KEY_QUERY, Constants.NetworkService.API_KEY_VALUE)
            .build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
