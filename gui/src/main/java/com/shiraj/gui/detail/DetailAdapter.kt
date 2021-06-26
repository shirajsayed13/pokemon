package com.shiraj.gui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiraj.core.model.Pokemon
import com.shiraj.core.model.PokemonDetail
import com.shiraj.gui.databinding.ItemDetailBinding
import com.shiraj.gui.databinding.ItemListingBinding
import com.shiraj.gui.loadImageFromUrl
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import kotlin.properties.Delegates

@FragmentScoped
internal class DetailAdapter @Inject constructor() :
    RecyclerView.Adapter<DetailAdapter.ListingViewHolder>() {

    internal var onFeedItemClickListener: (PokemonDetail.Stats) -> Unit = { }

    internal var feedItems: List<PokemonDetail.Stats> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun getItemCount(): Int = feedItems.size

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) =
        holder.bind(feedItems[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder =
        ListingViewHolder(
            ItemDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class ListingViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onFeedItemClickListener(
                    feedItems[adapterPosition]
                )
            }
        }

        fun bind(pokemon: PokemonDetail.Stats) {
            binding.apply {
                tvAbility.text = pokemon.baseStat.toString()
                tvName.text = pokemon.stat.name
            }
        }
    }
}