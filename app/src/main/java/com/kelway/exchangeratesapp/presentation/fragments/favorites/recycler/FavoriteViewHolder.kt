package com.kelway.exchangeratesapp.presentation.fragments.favorites.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kelway.exchangeratesapp.R
import com.kelway.exchangeratesapp.databinding.ItemCurrencyBinding
import com.kelway.exchangeratesapp.domain.model.CurrencyItem
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency
import com.kelway.exchangeratesapp.presentation.ExchangeRatesApplication
import javax.inject.Inject

class FavoriteViewHolder @Inject constructor(private val binding: ItemCurrencyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(parent: ViewGroup) = FavoriteViewHolder(
            ItemCurrencyBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(
                        R.layout.item_currency,
                        parent,
                        false
                    )
            )
        )
    }

    fun bindItem(favoriteCurrency: FavoriteCurrency) {
        ExchangeRatesApplication.appComponent?.inject(this)
        with(favoriteCurrency) {
            binding.nameCurrency.text = name_currency
            binding.valueCurrency.text = value_currency.toString()
        }
    }
}