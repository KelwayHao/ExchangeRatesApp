package com.kelway.exchangeratesapp.presentation.fragments.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelway.exchangeratesapp.domain.interactor.CurrencyInteractor
import com.kelway.exchangeratesapp.domain.interactor.FavoriteCurrencyInteractor
import com.kelway.exchangeratesapp.domain.model.Currency
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val interactor: CurrencyInteractor,
    private val favoriteCurrencyInteractor: FavoriteCurrencyInteractor
) :
    ViewModel() {

    val countState: StateFlow<Currency> = interactor.getDataCurrency()
        .stateIn(viewModelScope, SharingStarted.Lazily, Currency(null, emptyList()))

    fun addFavorite(favoriteCurrency: FavoriteCurrency) {
        viewModelScope.launch {
            favoriteCurrencyInteractor.saveData(favoriteCurrency)
        }
    }
}