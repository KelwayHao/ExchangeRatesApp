package com.kelway.exchangeratesapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kelway.exchangeratesapp.data.local.database.favorite.FavoriteDao
import com.kelway.exchangeratesapp.data.local.database.favorite.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = AppDatabase.VERSION_DB)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        const val VERSION_DB = 1
    }

    abstract fun getFavoriteDao(): FavoriteDao
}