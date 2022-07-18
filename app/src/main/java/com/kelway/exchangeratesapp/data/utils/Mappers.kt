package com.kelway.exchangeratesapp.data.utils

import com.kelway.exchangeratesapp.data.local.database.FavoriteEntity
import com.kelway.exchangeratesapp.domain.model.FavoriteCurrency

fun FavoriteEntity.toFavoriteCurrency(): FavoriteCurrency {
    return FavoriteCurrency(
        name_currency, value_currency, status_favorite
    )
}

fun FavoriteCurrency.toFavoriteEntity(): FavoriteEntity {
    return FavoriteEntity(
        nameCurrency, valueCurrency, statusFavorite
    )
}