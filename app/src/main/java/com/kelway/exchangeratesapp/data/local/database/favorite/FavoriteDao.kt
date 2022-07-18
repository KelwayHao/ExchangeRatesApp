package com.kelway.exchangeratesapp.data.local.database.favorite

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM currency_favorite")
    fun getAllFavoriteDao(): Flow<List<FavoriteEntity>>

    @Insert
    suspend fun saveFavoriteDao(favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavoriteDao(favoriteEntity: FavoriteEntity)

    @Update
    suspend fun updateFavoriteDao(favoriteEntity: FavoriteEntity)
}