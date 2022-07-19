package com.kelway.exchangeratesapp.presentation.fragments.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelway.exchangeratesapp.domain.interactor.CurrencyInteractor
import com.kelway.exchangeratesapp.domain.interactor.FavoriteCurrencyInteractor
import com.kelway.exchangeratesapp.domain.model.Currency
import com.kelway.exchangeratesapp.domain.model.CurrencyItem
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency
import com.kelway.exchangeratesapp.utils.currencySortedByAlphabet
import com.kelway.exchangeratesapp.utils.currencySortedByHighestValue
import com.kelway.exchangeratesapp.utils.currencySortedByLowestValue
import com.kelway.exchangeratesapp.utils.currencySortedByReverseAlphabet
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val interactor: CurrencyInteractor,
    private val favoriteCurrencyInteractor: FavoriteCurrencyInteractor
) :
    ViewModel() {

    init {
        sortAlphabet()
    }

    private var _countState: MutableStateFlow<Currency> =
        MutableStateFlow(Currency(null, emptyList()))
    val countState: StateFlow<Currency> = _countState.asStateFlow()

    fun addFavorite(favoriteCurrency: FavoriteCurrency) {
        viewModelScope.launch {
            favoriteCurrencyInteractor.saveData(favoriteCurrency)
        }
    }

    fun deleteFavorite(favoriteCurrency: FavoriteCurrency) {
        viewModelScope.launch {
            favoriteCurrencyInteractor.deleteData(favoriteCurrency)
        }
    }

    private fun getCurrency(): Flow<Currency> {
        return favoriteCurrencyInteractor.getAllData()
            .combine(interactor.getDataCurrency()) { favorite, popular ->
                val listFavorite: MutableList<String> = mutableListOf()
                favorite.map {
                    listFavorite.add(it.nameCurrency)
                }
                Currency(
                    popular.base,
                    popular.rates.map {
                        if (listFavorite.contains(it.nameCurrency)) {
                            CurrencyItem(
                                it.nameCurrency,
                                it.valueCurrency,
                                true
                            )
                        } else {
                            it
                        }
                    })
            }
    }


    fun sortAlphabet() {
        getCurrency().map { currency ->
            _countState.value = currency.currencySortedByAlphabet()
        }.launchIn(viewModelScope)
    }

    fun sortReverseAlphabet() {
        getCurrency().map { currency ->
            _countState.value = currency.currencySortedByReverseAlphabet()
        }.launchIn(viewModelScope)
    }

    fun sortPriceLowest() {
        getCurrency().map { currency ->
            _countState.value = currency.currencySortedByLowestValue()
        }.launchIn(viewModelScope)
    }

    fun sortPriceHighest() {
        getCurrency().map { currency ->
            _countState.value = currency.currencySortedByHighestValue()
        }.launchIn(viewModelScope)
    }
}