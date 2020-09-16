package com.musalaSoft.weather.ui.splash


import android.content.SharedPreferences
import com.musalaSoft.weather.core.BaseViewModel
import com.musalaSoft.weather.core.Constants
import com.musalaSoft.weather.db.entity.CoordEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Emel E. on 2020-09-01
 */

class SplashFragmentViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) : BaseViewModel() {
    var navigateDashboard = false

    fun saveCoordsToSharedPref(coordEntity: CoordEntity): Single<String>? {
        return Single.create<String> {
            sharedPreferences.edit().putString(Constants.Coords.LAT, coordEntity.lat.toString()).apply()
            sharedPreferences.edit().putString(Constants.Coords.LON, coordEntity.lon.toString()).apply()
            it.onSuccess("")
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}