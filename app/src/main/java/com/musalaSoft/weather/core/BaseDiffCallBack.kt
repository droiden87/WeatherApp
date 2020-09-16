package com.musalaSoft.weather.core

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Emel E. on 2020-09-01
 */
open class BaseDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}
