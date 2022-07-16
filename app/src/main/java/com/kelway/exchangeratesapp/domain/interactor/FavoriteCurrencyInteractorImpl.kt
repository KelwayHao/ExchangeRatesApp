package com.kelway.exchangeratesapp.domain.interactor

import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency
import com.kelway.exchangeratesapp.domain.repository.FavoriteCurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteCurrencyInteractorImpl @Inject constructor(private val favoriteCurrencyRepository: FavoriteCurrencyRepository) :
    FavoriteCurrencyInteractor {
    override fun getAllData(): Flow<List<FavoriteCurrency>> {
        return favoriteCurrencyRepository.getAllFavoriteCurrency()
    }

    override suspend fun saveData(favoriteCurrency: FavoriteCurrency) {
        favoriteCurrencyRepository.saveFavoriteCurrency(favoriteCurrency = favoriteCurrency)
    }

    override suspend fun deleteData(favoriteCurrency: FavoriteCurrency) {
        favoriteCurrencyRepository.deleteFavoriteCurrency(favoriteCurrency = favoriteCurrency)
    }

    override suspend fun updateData(favoriteCurrency: FavoriteCurrency) {
        favoriteCurrencyRepository.updateFavoriteCurrency(favoriteCurrency = favoriteCurrency)
    }

}