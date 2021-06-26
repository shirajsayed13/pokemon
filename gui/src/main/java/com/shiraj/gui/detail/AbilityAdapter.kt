package com.shiraj.gui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiraj.core.model.Pokemon
import com.shiraj.core.model.PokemonDetail
import com.shiraj.gui.databinding.ItemAbilityBinding
import com.shiraj.gui.databinding.ItemDetailBinding
import com.shiraj.gui.databinding.ItemListingBinding
import com.shiraj.gui.loadImageFromUrl
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import kotlin.properties.Delegates

@FragmentScoped
internal class AbilityAdapter @Inject constructor() :
    RecyclerView.Adapter<AbilityAdapter.ListingViewHolder>() {

    internal var onFeedItemClickListener: (PokemonDetail.AbilityData) -> Unit = { }

    internal var feedItems: List<PokemonDetail.AbilityData> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun getItemCount(): Int = feedItems.size

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) =
        holder.bind(feedItems[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder =
        ListingViewHolder(
            ItemAbilityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class ListingViewHolder(private val binding: ItemAbilityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onFeedItemClickListener(
                    feedItems[adapterPosition]
                )
            }
        }

        fun bind(pokemon: PokemonDetail.AbilityData) {
            binding.apply {
                tvName.text = pokemon.ability.name
            }
        }
    }
}