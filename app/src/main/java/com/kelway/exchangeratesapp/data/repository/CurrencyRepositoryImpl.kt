package com.kelway.exchangeratesapp.data.repository

import com.kelway.exchangeratesapp.data.model.CurrencyResponse
import com.kelway.exchangeratesapp.data.retrofit.ExchangeApiService
import com.kelway.exchangeratesapp.domain.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val exchangeApiService: ExchangeApiService):
    CurrencyRepository {
    override suspend fun getRepository(): CurrencyResponse {
        return withContext(Dispatchers.IO) {
            return@withContext exchangeApiService.getExchangeApi(base = "EUR")
        }
    }
}