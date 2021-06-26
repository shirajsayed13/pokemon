package com.shiraj.gui.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiraj.core.model.Pokemon
import com.shiraj.gui.databinding.ItemListingBinding
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import kotlin.properties.Delegates

@FragmentScoped
internal class ListingAdapter @Inject constructor() :
    RecyclerView.Adapter<ListingAdapter.ListingViewHolder>() {

    internal var onFeedItemClickListener: (Pokemon) -> Unit = { }

    internal var feedItems: List<Pokemon> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun getItemCount(): Int = feedItems.size

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) =
        holder.bind(feedItems[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder =
        ListingViewHolder(
            ItemListingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class ListingViewHolder(private val binding: ItemListingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onFeedItemClickListener(
                    feedItems[adapterPosition]
                )
            }
        }

        fun bind(pokemon: Pokemon) {
            binding.apply {
                tvName.text = pokemon.name
            }
        }
    }
}