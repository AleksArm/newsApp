package com.highestaim.newsapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.highestaim.newsapp.data.repositoryImpl.NewsRepositoryImpl
import com.highestaim.newsapp.dto.NewsDto
import com.highestaim.newsapp.utils.Converter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val  newsRepositoryImpl: NewsRepositoryImpl
) : ViewModel() {

    fun getNews(): Flow<PagingData<NewsDto>> =
        newsRepositoryImpl.getNews().cachedIn(viewModelScope)

    fun insertAsFavorite(newsDto : NewsDto) {
        viewModelScope.launch {
            newsRepositoryImpl.insertAsFavorite(
                Converter.favoriteEntityToDto(newsDto)
            )
        }
    }

    fun deleteFavorite(newsDto : NewsDto) {
        viewModelScope.launch {
            newsRepositoryImpl.delete(
                Converter.favoriteEntityToDto(newsDto)
            )
        }
    }

}