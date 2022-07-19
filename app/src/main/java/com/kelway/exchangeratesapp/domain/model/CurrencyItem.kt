package com.kelway.exchangeratesapp.domain.model

data class CurrencyItem(
    val nameCurrency: String,
    val valueCurrency: Double,
    var statusFavorite: Boolean
)

