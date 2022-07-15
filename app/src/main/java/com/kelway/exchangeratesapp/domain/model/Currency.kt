package com.kelway.exchangeratesapp.domain.model

data class Currency(
    var base: String? = null,
    var rates: List<CurrencyItem> = emptyList()
)