package com.kelway.exchangeratesapp.utils

import com.kelway.exchangeratesapp.domain.model.Currency
import com.kelway.exchangeratesapp.domain.model.CurrencyItem
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency

fun CurrencyItem.toFavoriteCurrency(): FavoriteCurrency {
    return FavoriteCurrency(
        nameCurrency,
        valueCurrency,
        true
    )
}

fun Currency.currencySortedByAlphabet() : Currency {
    return Currency(
        base,
        rates.sortedBy { currencyItem ->
            currencyItem.nameCurrency
        }
    )
}

fun Currency.currencySortedByReverseAlphabet() : Currency {
    return Currency(
        base,
        rates.sortedByDescending { currencyItem ->
            currencyItem.nameCurrency
        }
    )
}

fun Currency.currencySortedByLowestValue() : Currency {
    return Currency(
        base,
        rates.sortedBy { currencyItem ->
            currencyItem.valueCurrency
        }
    )
}

fun Currency.currencySortedByHighestValue() : Currency {
    return Currency(
        base,
        rates.sortedByDescending { currencyItem ->
            currencyItem.valueCurrency
        }
    )
}