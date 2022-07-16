package com.kelway.exchangeratesapp.presentation.fragments.favorites.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency

class FavoriteAdapter() : RecyclerView.Adapter<FavoriteViewHolder>() {
    private var items: List<FavoriteCurrency> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun submitItem(listItem: List<FavoriteCurrency>) {
        items = listItem
        notifyDataSetChanged()
    }
}