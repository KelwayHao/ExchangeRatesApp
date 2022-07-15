package com.kelway.exchangeratesapp.domain.interactor

import com.kelway.exchangeratesapp.domain.model.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyInteractor {
    fun getDataCurrency(): Flow<Currency>
}