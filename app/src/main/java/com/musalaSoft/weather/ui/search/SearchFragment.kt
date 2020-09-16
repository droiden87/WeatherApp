package com.musalaSoft.weather.ui.search

import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikhaellopez.rxanimation.backgroundColor
import com.musalaSoft.weather.R
import com.musalaSoft.weather.core.BaseFragment
import com.musalaSoft.weather.databinding.FragmentSearchBinding
import com.musalaSoft.weather.db.entity.CitiesForSearchEntity
import com.musalaSoft.weather.di.Injectable
import com.musalaSoft.weather.domain.usecase.SearchCitiesUseCase
import com.musalaSoft.weather.ui.main.MainActivity
import com.musalaSoft.weather.ui.search.result.SearchResultAdapter
import com.musalaSoft.weather.utils.extensions.hideKeyboard
import com.musalaSoft.weather.utils.extensions.observeWith
import com.musalaSoft.weather.utils.extensions.tryCatch


class SearchFragment() : BaseFragment<SearchViewModel, FragmentSearchBinding>(R.layout.fragment_search, SearchViewModel::class.java),
    Injectable,
    Parcelable {

    constructor(parcel: Parcel) : this() {
    }

    override fun init() {
        super.init()
        initSearchResultsAdapter()
        initSearchView()

        binding.viewModel?.getSearchViewState()?.observeWith(
            viewLifecycleOwner
        ) {
            binding.viewState = it
            it.data?.let { results -> initSearchResultsRecyclerView(results) }
        }
    }

    private fun initSearchView() {
        val searchEditText: EditText = binding.searchView.findViewById(R.id.search_src_text)
        activity?.applicationContext?.let { ContextCompat.getColor(it, R.color.mainTextColor) }
            ?.let { searchEditText.setTextColor(it) }
        activity?.applicationContext?.let { ContextCompat.getColor(it, android.R.color.darker_gray) }
            ?.let { searchEditText.setHintTextColor(it) }
        binding.searchView.isActivated = true
        binding.searchView.setIconifiedByDefault(false)
        binding.searchView.isIconified = false

        val searchViewSearchIcon = binding.searchView.findViewById<ImageView>(R.id.search_mag_icon)
        searchViewSearchIcon.setImageResource(R.drawable.ic_search)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String): Boolean {
                if (newText.isNotEmpty() && newText.count() > 1) {
                    binding.viewModel?.setSearchParams(SearchCitiesUseCase.SearchCitiesParams(newText))
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isNotEmpty() == true && newText.count() > 1) {
                    binding.viewModel?.setSearchParams(SearchCitiesUseCase.SearchCitiesParams(newText))
                }
                return true
            }
        })
    }

    private fun initSearchResultsAdapter() {
        val adapter = SearchResultAdapter { item ->
            item.coord?.let {
                binding.viewModel?.saveCoordsToSharedPref(it)
                    ?.subscribe { _, _ ->

                        tryCatch(
                            tryBlock = {
                                binding.searchView.hideKeyboard((activity as MainActivity))
                            }
                        )

                        findNavController().navigate(R.id.action_searchFragment_to_dashboardFragment)
                    }
            }
        }

        binding.recyclerViewSearchResults.adapter = adapter
        binding.recyclerViewSearchResults.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun initSearchResultsRecyclerView(list: List<CitiesForSearchEntity>) {
        (binding.recyclerViewSearchResults.adapter as SearchResultAdapter).submitList(list.distinctBy { it.getFullName() }.sortedBy { it.importance })
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchFragment> {
        override fun createFromParcel(parcel: Parcel): SearchFragment {
            return SearchFragment(parcel)
        }

        override fun newArray(size: Int): Array<SearchFragment?> {
            return arrayOfNulls(size)
        }
    }
}
