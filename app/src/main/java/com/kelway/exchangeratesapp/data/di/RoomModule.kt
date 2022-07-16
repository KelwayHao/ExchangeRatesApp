package com.kelway.exchangeratesapp.data.di

import android.content.Context
import androidx.room.Room
import com.kelway.exchangeratesapp.data.local.database.AppDatabase
import com.kelway.exchangeratesapp.data.local.database.FavoriteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "currency_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavoriteDao(appDatabase: AppDatabase): FavoriteDao {
        return appDatabase.getFavoriteDao()
    }
}