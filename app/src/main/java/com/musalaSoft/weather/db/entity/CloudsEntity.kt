package com.musalaSoft.weather.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.musalaSoft.weather.domain.model.Clouds
import kotlinx.android.parcel.Parcelize

/**
 * Created by Emel E. on 2020-09-01
 */
@Parcelize
@Entity(tableName = "Clouds")
data class CloudsEntity(
    @ColumnInfo(name = "all")
    var all: Int
) : Parcelable {
    @Ignore
    constructor(clouds: Clouds?) : this(
        all = clouds?.all ?: 0
    )
}