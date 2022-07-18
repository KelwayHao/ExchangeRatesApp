package com.kelway.exchangeratesapp.presentation.fragments.popular.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kelway.exchangeratesapp.R
import com.kelway.exchangeratesapp.databinding.ItemCurrencyBinding
import com.kelway.exchangeratesapp.domain.model.CurrencyItem
import com.kelway.exchangeratesapp.presentation.ExchangeRatesApplication
import com.kelway.exchangeratesapp.presentation.listener.AddFavoriteClickListener
import com.kelway.exchangeratesapp.presentation.listener.DeleteFavoriteClickListener
import com.kelway.exchangeratesapp.utils.toFavoriteCurrency
import javax.inject.Inject

class PopularViewHolder @Inject constructor(
    private val binding: ItemCurrencyBinding,
    private val addFavoriteCurrency: AddFavoriteClickListener,
    private val deleteFavoriteCurrency: DeleteFavoriteClickListener,
) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
            addFavoriteCurrency: AddFavoriteClickListener,
            deleteFavoriteCurrency: DeleteFavoriteClickListener,
        ) =
            PopularViewHolder(
                ItemCurrencyBinding.bind(
                    LayoutInflater.from(parent.context)
                        .inflate(
                            R.layout.item_currency,
                            parent,
                            false
                        )
                ),
                addFavoriteCurrency,
                deleteFavoriteCurrency
            )
    }


    fun bindItem(currencyItem: CurrencyItem) {
        ExchangeRatesApplication.appComponent?.inject(this)
        with(currencyItem) {
            binding.nameCurrency.text = nameCurrency
            binding.valueCurrency.text = valueCurrency.toString()
            if (statusFavorite) {
                binding.imageFavorite.setImageResource(R.drawable.ic_baseline_star_rate_24)
                binding.imageFavorite.setOnClickListener {
                    deleteFavoriteCurrency.clickAction(currencyItem.toFavoriteCurrency())
                    binding.imageFavorite.setImageResource(R.drawable.ic_baseline_star_border_24)
                }
            } else {
                binding.imageFavorite.setImageResource(R.drawable.ic_baseline_star_border_24)
                binding.imageFavorite.setOnClickListener {
                    addFavoriteCurrency.clickAction(currencyItem)
                    binding.imageFavorite.setImageResource(R.drawable.ic_baseline_star_rate_24)
                }
            }

        }
    }
}