package com.highestaim.newsapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.highestaim.newsapp.data.repositoryImpl.favorite.FavoriteRepositoryImpl
import com.highestaim.newsapp.dto.NewsDto
import com.highestaim.newsapp.utils.Converter.favoriteEntitiesToDto
import com.highestaim.newsapp.utils.Converter.favoriteEntityToDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoriteRepositoryImpl: FavoriteRepositoryImpl
) : ViewModel() {


    fun getFavorites(): StateFlow<List<NewsDto>?> {
        val data = MutableStateFlow<List<NewsDto>?>(null)
        viewModelScope.launch {
            favoriteRepositoryImpl.getAll.collect {
                data.value = favoriteEntitiesToDto(it)
            }
        }
         return data
    }

    fun insert(newsDto: NewsDto) {
        viewModelScope.launch {
            favoriteRepositoryImpl.insertAsFavorite(favoriteEntityToDto(newsDto))
        }
    }

    fun delete(newsDto: NewsDto) {
        viewModelScope.launch {
            favoriteRepositoryImpl.delete(favoriteEntityToDto(newsDto))
        }
    }
}