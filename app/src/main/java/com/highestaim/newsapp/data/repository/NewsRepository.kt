package com.highestaim.newsapp.data.repository

import androidx.paging.PagingData
import com.highestaim.newsapp.dto.NewsDto
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews() : Flow<PagingData<NewsDto>>
}