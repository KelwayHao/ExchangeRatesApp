package com.kelway.exchangeratesapp.presentation.fragments.popular.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kelway.exchangeratesapp.domain.model.CurrencyItem

class PopularAdapter() : RecyclerView.Adapter<PopularViewHolder>() {
    private var items: List<CurrencyItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun submitItem(listItem: List<CurrencyItem>) {
        items = listItem
        notifyDataSetChanged()
    }
}
