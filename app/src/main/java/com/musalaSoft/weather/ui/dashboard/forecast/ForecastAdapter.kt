package com.musalaSoft.weather.ui.dashboard.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.musalaSoft.weather.core.BaseAdapter
import com.musalaSoft.weather.databinding.ItemForecastBinding
import com.musalaSoft.weather.domain.model.ListItem

/**
 * Created by Emel E. on 2020-09-01
 */


class ForecastAdapter(private val callBack: (ListItem, View, View, View, View, View) -> Unit) : BaseAdapter<ListItem>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = ItemForecastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewModel = ForecastItemViewModel()
        mBinding.viewModel = viewModel

        mBinding.rootView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {

                callBack.invoke(
                    it,
                    mBinding.cardView,
                    mBinding.imageViewForecastIcon,
                    mBinding.textViewDayOfWeek,
                    mBinding.textViewTemp,
                    mBinding.linearLayoutTempMaxMin
                )
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemForecastBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<ListItem>() {
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem.dtTxt == newItem.dtTxt
}
