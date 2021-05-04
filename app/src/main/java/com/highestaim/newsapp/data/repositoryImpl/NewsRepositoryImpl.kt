package com.highestaim.newsapp.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.highestaim.newsapp.data.local.dao.FavoriteDao
import com.highestaim.newsapp.data.local.entity.FavoriteEntity
import com.highestaim.newsapp.data.remote.dataSource.NewsDataSource
import com.highestaim.newsapp.data.repository.NewsRepository
import com.highestaim.newsapp.data.remote.service.NewsService
import com.highestaim.newsapp.data.repository.favorite.FavoriteRepository
import com.highestaim.newsapp.dto.NewsDto
import com.highestaim.newsapp.utils.AppConstants.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsService: NewsService,
    private val favoriteDao: FavoriteDao
    ) : NewsRepository,FavoriteRepository {

    override fun getNews(): Flow<PagingData<NewsDto>> = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, initialLoadSize = NETWORK_PAGE_SIZE)
    ) {
        NewsDataSource(
            newsService = newsService
        )
    }.flow

    override suspend fun insertAsFavorite(favoriteEntity: FavoriteEntity) {
        favoriteDao.insert(favoriteEntity)
    }
}