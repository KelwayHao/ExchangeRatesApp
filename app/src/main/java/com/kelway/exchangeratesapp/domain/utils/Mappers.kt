package com.kelway.exchangeratesapp.domain.utils

import com.kelway.exchangeratesapp.data.model.CurrencyResponse
import com.kelway.exchangeratesapp.domain.model.Currency

fun CurrencyResponse.currencyResponseToCurrency(): Currency {
    return Currency(
        base, rates
    )
}