package com.musalaSoft.weather.ui.search

import com.musalaSoft.weather.core.BaseViewState
import com.musalaSoft.weather.db.entity.CitiesForSearchEntity
import com.musalaSoft.weather.utils.domain.Status

/**
 * Created by Emel E. on 2020-09-01
 */

class SearchViewState(
    val status: Status,
    val error: String? = null,
    val data: List<CitiesForSearchEntity>? = null
) : BaseViewState(status, error) {
    fun getSearchResult() = data
}
