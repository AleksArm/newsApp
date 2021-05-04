package com.highestaim.newsapp.data.repository.favorite

import com.highestaim.newsapp.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    val getAll: Flow<List<FavoriteEntity>>?
        get() = null

    suspend fun insertAsFavorite(favoriteEntity: FavoriteEntity) {}

    suspend fun delete(favoriteEntity: FavoriteEntity) {}
}