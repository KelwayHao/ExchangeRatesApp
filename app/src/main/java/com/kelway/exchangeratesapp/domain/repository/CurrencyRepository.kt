package com.kelway.exchangeratesapp.domain.repository

import com.kelway.exchangeratesapp.data.model.CurrencyResponse
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getRepository(baseCurrency: String): Flow<CurrencyResponse>
}