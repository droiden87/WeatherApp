package com.musalaSoft.weather.ui.search.result

import androidx.databinding.ObservableField
import com.musalaSoft.weather.core.BaseViewModel
import com.musalaSoft.weather.db.entity.CitiesForSearchEntity
import javax.inject.Inject

/**
 * Created by Emel E. on 2020-09-01
 */
class SearchResultItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<CitiesForSearchEntity>()
}
