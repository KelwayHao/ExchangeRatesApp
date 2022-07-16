package com.kelway.exchangeratesapp.presentation.fragments.popular.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kelway.exchangeratesapp.R
import com.kelway.exchangeratesapp.databinding.ItemCurrencyBinding
import com.kelway.exchangeratesapp.domain.model.CurrencyItem
import com.kelway.exchangeratesapp.presentation.ExchangeRatesApplication
import com.kelway.exchangeratesapp.presentation.listener.AddFavoriteClickListener
import javax.inject.Inject

class PopularViewHolder @Inject constructor(
    private val binding: ItemCurrencyBinding,
    private val addFavoriteCurrency: AddFavoriteClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(parent: ViewGroup, addFavoriteCurrency: AddFavoriteClickListener) =
            PopularViewHolder(
                ItemCurrencyBinding.bind(
                    LayoutInflater.from(parent.context)
                        .inflate(
                            R.layout.item_currency,
                            parent,
                            false
                        )
                ),
                addFavoriteCurrency
            )
    }

    fun bindItem(currencyItem: CurrencyItem) {
        ExchangeRatesApplication.appComponent?.inject(this)
        with(currencyItem) {
            binding.nameCurrency.text = nameCurrency
            binding.valueCurrency.text = valueCurrency.toString()
            binding.imageFavorite.setOnClickListener {
                addFavoriteCurrency.clickAction(currencyItem)
                binding.imageFavorite.setImageResource(R.drawable.ic_baseline_star_rate_24)
            }
        }
    }
}