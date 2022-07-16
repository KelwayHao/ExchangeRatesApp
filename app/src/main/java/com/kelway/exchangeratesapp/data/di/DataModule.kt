package com.kelway.exchangeratesapp.data.di

import com.kelway.exchangeratesapp.data.local.database.FavoriteDao
import com.kelway.exchangeratesapp.data.network.ExchangeApiService
import com.kelway.exchangeratesapp.data.repository.CurrencyRepositoryImpl
import com.kelway.exchangeratesapp.data.repository.FavoriteCurrencyRepositoryImpl
import com.kelway.exchangeratesapp.domain.repository.CurrencyRepository
import com.kelway.exchangeratesapp.domain.repository.FavoriteCurrencyRepository
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

    @Provides
    fun provideFavoriteCurrencyRepository(
        favoriteDao: FavoriteDao
    ): FavoriteCurrencyRepository {
        return FavoriteCurrencyRepositoryImpl(favoriteDao)
    }
}