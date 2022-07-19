package com.kelway.exchangeratesapp.presentation.fragments.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelway.exchangeratesapp.domain.interactor.CurrencyInteractor
import com.kelway.exchangeratesapp.domain.interactor.FavoriteCurrencyInteractor
import com.kelway.exchangeratesapp.domain.model.Currency
import com.kelway.exchangeratesapp.domain.model.CurrencyItem
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency
import com.kelway.exchangeratesapp.presentation.sort.SortType
import com.kelway.exchangeratesapp.utils.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val popularInteractor: CurrencyInteractor,
    private val favoriteInteractor: FavoriteCurrencyInteractor
) :
    ViewModel() {

    private var searchCurrency: String = Constants.BASE_CURRENCY

    init {
        sortAlphabet()
    }

    private var _favoriteState: MutableStateFlow<Currency> =
        MutableStateFlow(Currency(null, emptyList()))
    val favoriteState: StateFlow<Currency> = _favoriteState.asStateFlow()

    fun sortCurrency(sortType: SortType) {
        when (sortType) {
            SortType.NAME_UP -> sortAlphabet()
            SortType.NAME_DOWN -> sortReverseAlphabet()
            SortType.VALUE_UP -> sortPriceLowest()
            SortType.VALUE_DOWN -> sortPriceHighest()
        }
    }

    fun deleteFavorite(favoriteCurrency: FavoriteCurrency) {
        viewModelScope.launch {
            favoriteInteractor.deleteData(favoriteCurrency)
        }
    }

    fun setSearchValue(string: String) {
        searchCurrency = string
        getCurrency(string).map {
            _favoriteState.value = it
        }.launchIn(viewModelScope)
    }

    private fun getCurrency(q: String): Flow<Currency> {
        return favoriteInteractor.getAllData()
            .combine(popularInteractor.getDataCurrency(q)) { favorite, popular ->
                val listFavorite: MutableList<String> = mutableListOf()
                favorite.map {
                    listFavorite.add(it.nameCurrency)
                }

                val listSortFavorite: MutableList<CurrencyItem> = mutableListOf()
                popular.rates.map {
                    if (listFavorite.contains(it.nameCurrency)) {
                        listSortFavorite.add(
                            CurrencyItem(
                                it.nameCurrency,
                                it.valueCurrency,
                                true
                            )
                        )
                    }
                }

                Currency(
                    popular.base,
                    listSortFavorite.toList()
                )
            }
    }


    private fun sortAlphabet(q: String = searchCurrency) {
        getCurrency(q).map { currency ->
            _favoriteState.value = currency.currencySortedByAlphabet()
        }.launchIn(viewModelScope)
    }

    private fun sortReverseAlphabet(q: String = searchCurrency) {
        getCurrency(q).map { currency ->
            _favoriteState.value = currency.currencySortedByReverseAlphabet()
        }.launchIn(viewModelScope)
    }

    private fun sortPriceLowest(q: String = searchCurrency) {
        getCurrency(q).map { currency ->
            _favoriteState.value = currency.currencySortedByLowestValue()
        }.launchIn(viewModelScope)
    }

    private fun sortPriceHighest(q: String = searchCurrency) {
        getCurrency(q).map { currency ->
            _favoriteState.value = currency.currencySortedByHighestValue()
        }.launchIn(viewModelScope)
    }
}