package com.kelway.exchangeratesapp.domain.interactor

import com.kelway.exchangeratesapp.domain.model.Currency

interface CurrencyInteractor {
    suspend fun getDataCurrency(): Currency
}