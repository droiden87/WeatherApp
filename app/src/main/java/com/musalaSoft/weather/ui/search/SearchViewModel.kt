package com.musalaSoft.weather.ui.search

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.musalaSoft.weather.core.BaseViewModel
import com.musalaSoft.weather.core.Constants
import com.musalaSoft.weather.db.entity.CoordEntity
import com.musalaSoft.weather.domain.usecase.SearchCitiesUseCase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Emel E. on 2020-09-01
 */

class SearchViewModel @Inject internal constructor(private val useCase: SearchCitiesUseCase, private val pref: SharedPreferences) : BaseViewModel() {

    private val _searchParams: MutableLiveData<SearchCitiesUseCase.SearchCitiesParams> = MutableLiveData()
    fun getSearchViewState() = searchViewState

    private val searchViewState: LiveData<SearchViewState> = _searchParams.switchMap { params ->
        useCase.execute(params)
    }

    fun setSearchParams(params: SearchCitiesUseCase.SearchCitiesParams) {
        if (_searchParams.value == params)
            return
        _searchParams.postValue(params)
    }

    fun saveCoordsToSharedPref(coordEntity: CoordEntity): Single<String>? {
        return Single.create<String> {
            pref.edit().putString(Constants.Coords.LAT, coordEntity.lat.toString()).apply()
            pref.edit().putString(Constants.Coords.LON, coordEntity.lon.toString()).apply()
            it.onSuccess("")
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}
