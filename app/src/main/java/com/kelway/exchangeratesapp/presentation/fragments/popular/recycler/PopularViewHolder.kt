package com.kelway.exchangeratesapp.presentation.fragments.popular.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kelway.exchangeratesapp.R
import com.kelway.exchangeratesapp.databinding.ItemCurrencyBinding
import com.kelway.exchangeratesapp.domain.model.CurrencyItem
import com.kelway.exchangeratesapp.presentation.ExchangeRatesApplication
import javax.inject.Inject

class PopularViewHolder @Inject constructor(private val binding: ItemCurrencyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(parent: ViewGroup) = PopularViewHolder(
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

    fun bindItem(currencyItem: CurrencyItem) {
        ExchangeRatesApplication.appComponent?.inject(this)
        with(currencyItem) {
            binding.nameCurrency.text = nameCurrency
            binding.valueCurrency.text = valueCurrency.toString()
        }
    }
}