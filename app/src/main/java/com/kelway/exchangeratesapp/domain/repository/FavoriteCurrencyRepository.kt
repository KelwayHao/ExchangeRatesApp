package com.kelway.exchangeratesapp.domain.repository

import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency
import kotlinx.coroutines.flow.Flow

interface FavoriteCurrencyRepository {

    fun getAllFavoriteCurrency(): Flow<List<FavoriteCurrency>>

    suspend fun saveFavoriteCurrency(favoriteCurrency: FavoriteCurrency)

    suspend fun deleteFavoriteCurrency(favoriteCurrency: FavoriteCurrency)

    suspend fun updateFavoriteCurrency(favoriteCurrency: FavoriteCurrency)
}