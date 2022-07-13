package com.kelway.exchangeratesapp.data.repository

import com.kelway.exchangeratesapp.data.model.CurrencyResponse
import com.kelway.exchangeratesapp.data.retrofit.ExchangeApiService
import com.kelway.exchangeratesapp.domain.repository.CurrencyRepository
import java.util.concurrent.Flow
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val exchangeApiService: ExchangeApiService) :
    CurrencyRepository {
    override fun getRepository(): Flow<CurrencyResponse> {
        return exchangeApiService.getExchangeApi(base = "EUR")
    }
}