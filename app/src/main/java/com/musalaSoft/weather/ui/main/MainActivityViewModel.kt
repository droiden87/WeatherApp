package com.musalaSoft.weather.ui.main

import android.content.SharedPreferences
import androidx.databinding.ObservableField
import com.musalaSoft.weather.core.BaseViewModel
import com.musalaSoft.weather.core.Constants
import com.musalaSoft.weather.db.entity.CoordEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel @Inject internal constructor(private val sharedPreferences: SharedPreferences) : BaseViewModel() {
    var toolbarTitle: ObservableField<String> = ObservableField()

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