package com.kelway.exchangeratesapp.presentation.fragments.popular

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelway.exchangeratesapp.domain.interactor.CurrencyInteractor
import com.kelway.exchangeratesapp.domain.interactor.FavoriteCurrencyInteractor
import com.kelway.exchangeratesapp.domain.model.Currency
import com.kelway.exchangeratesapp.domain.model.CurrencyItem
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency
import com.kelway.exchangeratesapp.utils.toFavoriteCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val interactor: CurrencyInteractor,
    private val favoriteCurrencyInteractor: FavoriteCurrencyInteractor
) :
    ViewModel() {

    /*init {
        getFavoriteList()
    }*/

    val countState: StateFlow<Currency> = testFlow()
        .stateIn(viewModelScope, SharingStarted.Lazily, Currency(null, emptyList()))

    fun addFavorite(favoriteCurrency: FavoriteCurrency) {
        viewModelScope.launch {
            favoriteCurrencyInteractor.saveData(favoriteCurrency)
        }
    }


    /*private fun getFavoriteList() {
        favoriteCurrencyInteractor.getAllData()
            .map { _listFavorite.postValue(it) }
            .launchIn(viewModelScope)
    }*/

    fun deleteCurrency(favoriteCurrency: FavoriteCurrency) {
        viewModelScope.launch {
            favoriteCurrencyInteractor.deleteData(favoriteCurrency)
        }
    }


    private fun testFlow(): Flow<Currency> {
        return favoriteCurrencyInteractor.getAllData()
            .combine(interactor.getDataCurrency()) { b, a ->
                val listFavorite: MutableList<String> = mutableListOf()
                Log.e("b", b.toString())
                b.map {
                    listFavorite.add(it.nameCurrency)
                }
                Currency(
                    a.base,
                    a.rates.map {
                        if (listFavorite.contains(it.nameCurrency)) {
                            Log.e("true", it.nameCurrency)
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

    fun FavoriteCurrency.toCurrencyItem(): CurrencyItem {
        return CurrencyItem(
            nameCurrency,
            valueCurrency,
            statusFavorite
        )
    }
}