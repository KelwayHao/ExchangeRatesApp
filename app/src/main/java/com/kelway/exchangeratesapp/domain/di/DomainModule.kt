package com.kelway.exchangeratesapp.domain.di

import com.kelway.exchangeratesapp.domain.interactor.CurrencyInteractor
import com.kelway.exchangeratesapp.domain.interactor.CurrencyInteractorImpl
import com.kelway.exchangeratesapp.domain.interactor.FavoriteCurrencyInteractor
import com.kelway.exchangeratesapp.domain.interactor.FavoriteCurrencyInteractorImpl
import com.kelway.exchangeratesapp.domain.repository.CurrencyRepository
import com.kelway.exchangeratesapp.domain.repository.FavoriteCurrencyRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideCurrencyInteractor(
        currencyRepository: CurrencyRepository
    ): CurrencyInteractor {
        return CurrencyInteractorImpl(currencyRepository)
    }

    @Provides
    fun provideFavoriteCurrencyInteractor(
        favoriteCurrencyRepository: FavoriteCurrencyRepository
    ): FavoriteCurrencyInteractor {
        return FavoriteCurrencyInteractorImpl(favoriteCurrencyRepository = favoriteCurrencyRepository)
    }
}