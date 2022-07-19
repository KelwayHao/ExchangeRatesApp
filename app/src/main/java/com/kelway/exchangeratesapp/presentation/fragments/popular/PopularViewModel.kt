package com.kelway.exchangeratesapp.presentation.fragments.popular

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelway.exchangeratesapp.domain.interactor.CurrencyInteractor
import com.kelway.exchangeratesapp.domain.interactor.FavoriteCurrencyInteractor
import com.kelway.exchangeratesapp.domain.model.Currency
import com.kelway.exchangeratesapp.domain.model.CurrencyItem
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency
import com.kelway.exchangeratesapp.utils.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class PopularViewModel @Inject constructor(
    private val interactor: CurrencyInteractor,
    private val favoriteCurrencyInteractor: FavoriteCurrencyInteractor
) :
    ViewModel() {

    private var searchCurrency: String = Constants.BASE_CURRENCY

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

    fun setSearchValue(string: String){
        searchCurrency = string
        getCurrency(string).map {
            _countState.value = it
        }.launchIn(viewModelScope)
        Log.e("test", string)
    }

    private fun getCurrency(q: String): Flow<Currency> {
        return favoriteCurrencyInteractor.getAllData()
            .combine(interactor.getDataCurrency(q)) { favorite, popular ->
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


    fun sortAlphabet(q: String = searchCurrency) {
        getCurrency(q).map { currency ->
            _countState.value = currency.currencySortedByAlphabet()
        }.launchIn(viewModelScope)
    }

    fun sortReverseAlphabet(q: String = searchCurrency) {
        getCurrency(q).map { currency ->
            _countState.value = currency.currencySortedByReverseAlphabet()
        }.launchIn(viewModelScope)
    }

    fun sortPriceLowest(q: String = searchCurrency) {
        getCurrency(q).map { currency ->
            _countState.value = currency.currencySortedByLowestValue()
        }.launchIn(viewModelScope)
    }

    fun sortPriceHighest(q: String = searchCurrency) {
        getCurrency(q).map { currency ->
            _countState.value = currency.currencySortedByHighestValue()
        }.launchIn(viewModelScope)
    }
}