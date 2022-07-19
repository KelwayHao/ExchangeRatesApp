package com.kelway.exchangeratesapp.domain.interactor

import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency
import kotlinx.coroutines.flow.Flow

interface FavoriteCurrencyInteractor {
    fun getAllData(): Flow<List<FavoriteCurrency>>

    suspend fun saveData(favoriteCurrency: FavoriteCurrency)

    suspend fun deleteData(favoriteCurrency: FavoriteCurrency)

    suspend fun updateData(favoriteCurrency: FavoriteCurrency)
}