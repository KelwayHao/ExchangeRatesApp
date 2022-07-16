package com.kelway.exchangeratesapp.utils

import com.kelway.exchangeratesapp.domain.model.CurrencyItem
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency

fun CurrencyItem.toFavoriteCurrency(): FavoriteCurrency {
    return FavoriteCurrency(
        nameCurrency,
        valueCurrency,
        true
    )
}