package com.kelway.exchangeratesapp.domain.di

import com.kelway.exchangeratesapp.domain.interactor.CurrencyInteractor
import com.kelway.exchangeratesapp.domain.interactor.CurrencyInteractorImpl
import com.kelway.exchangeratesapp.domain.repository.CurrencyRepository
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
}