package com.shiraj.gui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewbinding.ViewBinding
import com.shiraj.base.failure
import com.shiraj.base.fragment.BaseFragment
import com.shiraj.base.observe
import com.shiraj.core.model.PokemonDetail
import com.shiraj.core.webservice.WebServiceFailure
import com.shiraj.gui.AppToast
import com.shiraj.gui.R
import com.shiraj.gui.databinding.FragmentDetailBinding
import com.shiraj.gui.loadImageFromUrl
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    override val layoutResId: Int
        get() = R.layout.fragment_detail

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding
        get() = FragmentDetailBinding::inflate

    override val binding: FragmentDetailBinding
        get() = super.binding as FragmentDetailBinding

    private val viewModel: DetailViewModel by viewModels()

    @Inject
    internal lateinit var detailAdapter: DetailAdapter

    @Inject
    internal lateinit var abilityAdapter: AbilityAdapter

    private val args: DetailFragmentArgs by navArgs()

    override fun onInitView() {

        viewModel.apply {
            failure(failure, ::handleFailure)
            observe(feedItems, ::showPokemonDetail)
            loadDetail("1")
        }

        binding.apply {
            rvStats.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = detailAdapter
            }

            rvAbility.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = abilityAdapter
            }
        }
    }

    private fun showPokemonDetail(pokemonDetail: PokemonDetail) {
        detailAdapter.feedItems = pokemonDetail.stats
        abilityAdapter.feedItems = pokemonDetail.abilityData
        binding.apply {
            val pokemon = args.pokemon
            if (pokemon != null) {
                tvName.text = pokemon.name
                tvHeight.text = "Height ${pokemonDetail.getHeightString()}"
                tvWeight.text = "Weight ${pokemonDetail.getWeightString()}"
                ivPokemon.loadImageFromUrl(pokemon.getImageUrl())
            }
        }
    }

    private fun handleFailure(e: Exception?) {
        Timber.v("handleFailure: IN")
        Timber.e(e)
        when (e) {
            is WebServiceFailure.NoNetworkFailure -> showErrorToast("No internet connection!")
            is WebServiceFailure.NetworkTimeOutFailure, is WebServiceFailure.NetworkDataFailure -> showErrorToast(
                "Internal server error!"
            )
            else -> showErrorToast("Unknown error occurred!")
        }
        Timber.v("handleFailure: OUT")
    }

    private fun Fragment.showErrorToast(msg: String) {
        AppToast.show(requireContext(), msg, Toast.LENGTH_SHORT)
    }


}