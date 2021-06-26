package com.shiraj.gui.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shiraj.base.viewmodel.BaseViewModel
import com.shiraj.core.usecase.GetListingUseCase
import com.shiraj.core.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class ListingViewModel @Inject constructor(
    private val getListingUseCase: GetListingUseCase
) : BaseViewModel() {

    private val _feedItems: MutableLiveData<List<Pokemon>> by lazy { MutableLiveData() }
    internal val feedItems: LiveData<List<Pokemon>> = _feedItems

    internal fun loadListing(page: Int) {
        Timber.d("loadListing: ")
        launchUseCase {
            _feedItems.postValue(getListingUseCase(page))
        }
    }
}