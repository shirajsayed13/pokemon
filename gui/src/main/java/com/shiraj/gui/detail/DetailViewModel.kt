package com.shiraj.gui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shiraj.base.viewmodel.BaseViewModel
import com.shiraj.core.model.PokemonDetail
import com.shiraj.core.usecase.GetPokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : BaseViewModel() {
    private val _feedItems: MutableLiveData<PokemonDetail> by lazy { MutableLiveData() }
    internal val feedItems: LiveData<PokemonDetail> = _feedItems

    internal fun loadListing(id: Int) {
        Timber.d("loadListing: ")
        launchUseCase {
            _feedItems.postValue(getPokemonDetailUseCase(id))
        }
    }
}