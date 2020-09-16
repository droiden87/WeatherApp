package com.musalaSoft.weather.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.musalaSoft.weather.domain.model.Wind
import kotlinx.android.parcel.Parcelize

/**
 * Created by Emel E. on 2020-09-01
 */

@Parcelize
@Entity(tableName = "Wind")
data class WindEntity(
    @ColumnInfo(name = "deg")
    val deg: Double?,
    @ColumnInfo(name = "speed")
    val speed: Double?
) : Parcelable {
    @Ignore
    constructor(wind: Wind?) : this(
        deg = wind?.deg,
        speed = wind?.speed
    )
}
