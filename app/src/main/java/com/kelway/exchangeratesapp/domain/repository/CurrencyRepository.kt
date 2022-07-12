package com.kelway.exchangeratesapp.domain.repository

import com.kelway.exchangeratesapp.data.model.CurrencyResponse

interface CurrencyRepository {
    suspend fun getRepository(): CurrencyResponse
}