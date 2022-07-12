package com.kelway.exchangeratesapp.domain.model

import com.kelway.exchangeratesapp.data.model.Rates

data class Currency(
    var base: String? = null,
    var rates: Rates? = Rates()
)