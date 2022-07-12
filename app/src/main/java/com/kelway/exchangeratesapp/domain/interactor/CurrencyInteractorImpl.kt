package com.kelway.exchangeratesapp.domain.interactor

import com.kelway.exchangeratesapp.domain.model.Currency
import com.kelway.exchangeratesapp.domain.repository.CurrencyRepository
import com.kelway.exchangeratesapp.domain.utils.CurrencyResponseToCurrency
import javax.inject.Inject

class CurrencyInteractorImpl @Inject constructor(private val repository: CurrencyRepository) :
    CurrencyInteractor {
    override suspend fun getDataCurrency(): Currency {
        return repository.getRepository().CurrencyResponseToCurrency()
    }
}