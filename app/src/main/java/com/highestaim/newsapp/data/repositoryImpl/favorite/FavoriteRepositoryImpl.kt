package com.highestaim.newsapp.data.repositoryImpl.favorite

import com.highestaim.newsapp.data.local.dao.FavoriteDao
import com.highestaim.newsapp.data.local.entity.FavoriteEntity
import com.highestaim.newsapp.data.repository.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow

class FavoriteRepositoryImpl(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {

    override val getAll: Flow<List<FavoriteEntity>>
        get() = favoriteDao.getFavorites()

    override suspend fun insertAsFavorite(favoriteEntity: FavoriteEntity) {
        favoriteDao.insert(favoriteEntity)
    }

    override suspend fun delete(favoriteEntity: FavoriteEntity) {
        favoriteDao.delete(favoriteEntity)
    }
}