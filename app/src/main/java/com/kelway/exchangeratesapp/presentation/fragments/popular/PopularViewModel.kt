package com.kelway.exchangeratesapp.presentation.fragments.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelway.exchangeratesapp.domain.interactor.CurrencyInteractor
import com.kelway.exchangeratesapp.domain.model.Currency
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class PopularViewModel @Inject constructor(private val interactor: CurrencyInteractor) :
    ViewModel() {

    val countState: StateFlow<Currency> = interactor.getDataCurrency()
        .stateIn(viewModelScope, SharingStarted.Lazily, Currency(null, emptyList()))

}