package com.kelway.exchangeratesapp.presentation.fragments.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelway.exchangeratesapp.domain.interactor.CurrencyInteractor
import com.kelway.exchangeratesapp.domain.model.Currency
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularViewModel @Inject constructor(private val interactor: CurrencyInteractor) :
    ViewModel() {

    /*private val _rates = MutableLiveData<Currency>()
    val rates: LiveData<Currency> get() = _rates*/

    private val _countState = MutableStateFlow<Currency>(Currency(null, null))
    val countState: StateFlow<Currency> = interactor.getDataCurrency()
        .stateIn(viewModelScope, SharingStarted.Lazily, Currency(null, null))

    /*fun loadData() {
        viewModelScope.launch {
            _rates.postValue(interactor.getDataCurrency())
        }
    }*/

    /*fun loadData() {
        viewModelScope.launch {
            _countState.value = interactor.getDataCurrency().stateIn(viewModelScope).
        }
    }*/
}