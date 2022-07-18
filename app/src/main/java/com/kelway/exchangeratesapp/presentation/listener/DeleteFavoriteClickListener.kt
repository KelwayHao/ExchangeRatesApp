package com.kelway.exchangeratesapp.presentation.listener

import com.kelway.exchangeratesapp.domain.model.CurrencyItem
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency

interface DeleteFavoriteClickListener {
    fun clickAction(favoriteCurrency: FavoriteCurrency)
}