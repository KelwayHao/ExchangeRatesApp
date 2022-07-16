package com.kelway.exchangeratesapp.presentation.listener

import com.kelway.exchangeratesapp.domain.model.CurrencyItem

interface AddFavoriteClickListener {
    fun clickAction(currencyItem: CurrencyItem)
}