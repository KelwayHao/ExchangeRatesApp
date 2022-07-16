package com.kelway.exchangeratesapp.domain.model

data class FavoriteCurrency(
    val name_currency: String,
    val value_currency: Double,
    val status_favorite: Boolean
)
