package com.kelway.exchangeratesapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteEntity::class], version = AppDatabase.VERSION_DB)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        const val VERSION_DB = 1
    }

    abstract fun getFavoriteDao(): FavoriteDao
}