package com.kelway.exchangeratesapp.data.repository

import com.kelway.exchangeratesapp.data.model.CurrencyResponse
import com.kelway.exchangeratesapp.data.network.ExchangeApiService
import com.kelway.exchangeratesapp.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val exchangeApiService: ExchangeApiService) :
    CurrencyRepository {
    override fun getRepository(baseCurrency: String): Flow<CurrencyResponse> {
        return exchangeApiService.getExchangeApi(base = baseCurrency)
    }
}