package com.kelway.exchangeratesapp.domain.interactor

import com.kelway.exchangeratesapp.data.model.CurrencyResponse
import com.kelway.exchangeratesapp.domain.model.Currency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface CurrencyInteractor {
    suspend fun getDataCurrency(): Flow<Currency>
}