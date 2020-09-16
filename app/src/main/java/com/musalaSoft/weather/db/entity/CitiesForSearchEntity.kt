package com.musalaSoft.weather.db.entity

import android.os.Parcelable
import android.text.SpannableString
import androidx.room.*
import com.musalaSoft.weather.domain.model.HitsItem
import com.musalaSoft.weather.utils.extensions.bold
import com.musalaSoft.weather.utils.extensions.italic
import com.musalaSoft.weather.utils.extensions.plus
import com.musalaSoft.weather.utils.extensions.spannable

import kotlinx.android.parcel.Parcelize
/**
 * Created by Emel E. on 2020-09-01
 */

@Parcelize
@Entity(tableName = "CitiesForSearch")
data class CitiesForSearchEntity(
    @ColumnInfo(name = "administrative")
    val administrative: String?,
    @ColumnInfo(name = "Country")
    val country: String?,
    @Embedded
    val coord: CoordEntity?,
    @ColumnInfo(name = "fullName")
    val name: String?,
    @ColumnInfo(name = "county")
    val county: String?,
    @ColumnInfo(name = "importance")
    val importance: Int?,
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id: String
) : Parcelable {
    @Ignore
    constructor(hitsItem: HitsItem?) : this(
        country = hitsItem?.country,
        importance = hitsItem?.importance,
        administrative = hitsItem?.administrative?.first(),
        coord = CoordEntity(hitsItem?.geoloc),
        name = hitsItem?.localeNames?.first(),
        county = hitsItem?.county?.first(),
        id = hitsItem?.objectID.toString()
    )

    fun getFullName(): SpannableString {
        return spannable {
            bold(name ?: "").plus(", ") +
                    bold(county ?: "").plus(", ") +
                    italic(administrative ?: "").plus(", ") +
                    italic(country ?: "")
        }
    }
}
