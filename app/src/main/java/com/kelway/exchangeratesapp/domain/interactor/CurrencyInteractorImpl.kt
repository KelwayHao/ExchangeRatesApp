package com.kelway.exchangeratesapp.domain.interactor

import com.kelway.exchangeratesapp.domain.model.Currency
import com.kelway.exchangeratesapp.domain.repository.CurrencyRepository
import com.kelway.exchangeratesapp.domain.utils.currencyResponseToCurrency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyInteractorImpl @Inject constructor(private val repository: CurrencyRepository) :
    CurrencyInteractor {
    override fun getDataCurrency(baseCurrency: String): Flow<Currency> {
        return repository.getRepository(baseCurrency).map {
            it.currencyResponseToCurrency()
        }
    }
}