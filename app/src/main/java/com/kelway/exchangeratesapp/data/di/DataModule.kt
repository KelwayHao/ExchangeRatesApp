package com.kelway.exchangeratesapp.data.di

import com.kelway.exchangeratesapp.data.repository.CurrencyRepositoryImpl
import com.kelway.exchangeratesapp.data.retrofit.ExchangeApiService
import com.kelway.exchangeratesapp.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideCurrencyRepository(
        exchangeApiService: ExchangeApiService
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(exchangeApiService = exchangeApiService)
    }
}