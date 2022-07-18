package com.kelway.exchangeratesapp.presentation.fragments.favorites.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kelway.exchangeratesapp.R
import com.kelway.exchangeratesapp.databinding.ItemCurrencyBinding
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency
import com.kelway.exchangeratesapp.presentation.ExchangeRatesApplication
import com.kelway.exchangeratesapp.presentation.listener.DeleteFavoriteClickListener
import javax.inject.Inject

class FavoriteViewHolder @Inject constructor(
    private val binding: ItemCurrencyBinding,
    private val deleteFavoriteCurrency: DeleteFavoriteClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(parent: ViewGroup, deleteFavoriteCurrency: DeleteFavoriteClickListener) =
            FavoriteViewHolder(
                ItemCurrencyBinding.bind(
                    LayoutInflater.from(parent.context)
                        .inflate(
                            R.layout.item_currency,
                            parent,
                            false
                        )
                ),
                deleteFavoriteCurrency
            )
    }

    fun bindItem(favoriteCurrency: FavoriteCurrency) {
        ExchangeRatesApplication.appComponent?.inject(this)
        with(favoriteCurrency) {
            binding.nameCurrency.text = name_currency
            binding.valueCurrency.text = value_currency.toString()
            binding.imageFavorite.setImageResource(R.drawable.ic_baseline_star_rate_24)
            binding.imageFavorite.setOnClickListener {
                deleteFavoriteCurrency.clickAction(favoriteCurrency)
            }
        }
    }
}