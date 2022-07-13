package com.kelway.exchangeratesapp.domain.repository

import com.kelway.exchangeratesapp.data.model.CurrencyResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface CurrencyRepository {
    fun getRepository(): Flow<CurrencyResponse>
}