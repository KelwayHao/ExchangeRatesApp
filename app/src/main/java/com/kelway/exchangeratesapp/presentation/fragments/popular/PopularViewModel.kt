package com.kelway.exchangeratesapp.presentation.fragments.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelway.exchangeratesapp.domain.interactor.CurrencyInteractor
import com.kelway.exchangeratesapp.domain.interactor.FavoriteCurrencyInteractor
import com.kelway.exchangeratesapp.domain.model.Currency
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val interactor: CurrencyInteractor,
    private val favoriteCurrencyInteractor: FavoriteCurrencyInteractor
) :
    ViewModel() {

    init {
        getFavoriteList()
    }

    val countState: StateFlow<Currency> = interactor.getDataCurrency()
        .stateIn(viewModelScope, SharingStarted.Lazily, Currency(null, emptyList()))

    private val _listFavorite = MutableLiveData<List<FavoriteCurrency>>()
    val listFavorite: List<FavoriteCurrency> get() = _listFavorite.value ?: emptyList()

    fun addFavorite(favoriteCurrency: FavoriteCurrency) {
        viewModelScope.launch {
            favoriteCurrencyInteractor.saveData(favoriteCurrency)
        }
    }

    private fun getFavoriteList() {
        favoriteCurrencyInteractor.getAllData()
            .map { _listFavorite.postValue(it) }
            .launchIn(viewModelScope)
    }

    fun deleteCurrency(favoriteCurrency: FavoriteCurrency) {
        viewModelScope.launch {
            favoriteCurrencyInteractor.deleteData(favoriteCurrency)
        }
    }
}