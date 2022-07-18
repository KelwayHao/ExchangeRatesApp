package com.kelway.exchangeratesapp.data.repository

import com.kelway.exchangeratesapp.data.local.database.favorite.FavoriteDao
import com.kelway.exchangeratesapp.data.utils.toFavoriteCurrency
import com.kelway.exchangeratesapp.data.utils.toFavoriteEntity
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency
import com.kelway.exchangeratesapp.domain.repository.FavoriteCurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteCurrencyRepositoryImpl @Inject constructor(private val favoriteDao: FavoriteDao) :
    FavoriteCurrencyRepository {
    override fun getAllFavoriteCurrency(): Flow<List<FavoriteCurrency>> {
        return favoriteDao.getAllFavoriteDao().map { list ->
            list.map { favoriteEntity ->
                favoriteEntity.toFavoriteCurrency()
            }
        }
    }

    override suspend fun saveFavoriteCurrency(favoriteCurrency: FavoriteCurrency) {
        return withContext(Dispatchers.IO) {
            favoriteDao.saveFavoriteDao(favoriteCurrency.toFavoriteEntity())
        }
    }

    override suspend fun deleteFavoriteCurrency(favoriteCurrency: FavoriteCurrency) {
        return withContext(Dispatchers.IO) {
            favoriteDao.deleteFavoriteDao(favoriteCurrency.toFavoriteEntity())
        }
    }

    override suspend fun updateFavoriteCurrency(favoriteCurrency: FavoriteCurrency) {
        return withContext(Dispatchers.IO) {
            favoriteDao.updateFavoriteDao(favoriteCurrency.toFavoriteEntity())
        }
    }

}