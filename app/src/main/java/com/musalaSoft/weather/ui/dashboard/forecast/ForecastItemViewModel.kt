package com.musalaSoft.weather.ui.dashboard.forecast

import androidx.databinding.ObservableField
import com.musalaSoft.weather.core.BaseViewModel
import com.musalaSoft.weather.domain.model.ListItem
import javax.inject.Inject

/**
 * Created by Emel E. on 2020-09-01
 */

class ForecastItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<ListItem>()
}
