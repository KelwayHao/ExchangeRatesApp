package com.kelway.exchangeratesapp.presentation.fragments.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelway.exchangeratesapp.domain.interactor.FavoriteCurrencyInteractor
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val interactor: FavoriteCurrencyInteractor) :
    ViewModel() {

    val favoriteState: StateFlow<List<FavoriteCurrency>> = interactor.getAllData()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}