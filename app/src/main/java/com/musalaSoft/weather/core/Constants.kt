package com.musalaSoft.weather.core
/**
 * Created by Emel E. on 2020-09-01
 */

object Constants {

        object NetworkService {
            const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
            const val API_KEY_VALUE = "345424783af3df962b50710de49aa711"
            const val RATE_LIMITER_TYPE = "data"
            const val API_KEY_QUERY = "appid"
        }

        object AlgoliaKeys {
            const val APPLICATION_ID = "plNW8IW0YOIN"
            const val SEARCH_API_KEY = "029766644cb160efa51f2a32284310eb"
        }

        object Coords {
            const val LAT = "lat"
            const val LON = "lon"
            const val METRIC = "metric"
        }
    }