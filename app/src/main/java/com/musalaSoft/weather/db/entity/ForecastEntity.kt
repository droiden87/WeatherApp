package com.musalaSoft.weather.db.entity

import android.os.Parcelable
import androidx.room.*
import com.musalaSoft.weather.domain.model.ListItem
import com.musalaSoft.weather.domain.model.response.ForecastResponse
import kotlinx.android.parcel.Parcelize

/**
 * Created by Emel E. on 2020-09-01
 */



@Parcelize
@Entity(tableName = "Forecast")
data class ForecastEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @Embedded
    var city: CityEntity?,

    @ColumnInfo(name = "list")
    var list: List<ListItem>?
) : Parcelable {

    @Ignore
    constructor(forecastResponse: ForecastResponse) : this(
        id = 0,
        city = forecastResponse.city?.let { CityEntity(it) },
        list = forecastResponse.list
    )
}
