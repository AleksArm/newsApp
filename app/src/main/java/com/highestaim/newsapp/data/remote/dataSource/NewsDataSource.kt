package com.highestaim.newsapp.data.remote.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.highestaim.newsapp.data.remote.model.NewsModel
import com.highestaim.newsapp.data.remote.service.NewsService
import com.highestaim.newsapp.dto.NewsDto
import com.highestaim.newsapp.utils.AppConstants.NETWORK_PAGE_SIZE
import com.highestaim.newsapp.utils.Converter
import retrofit2.HttpException
import java.io.IOException

class NewsDataSource(
  private val newsService: NewsService
) : PagingSource<Int,NewsDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsDto>  = try {
        val news: List<NewsModel>  = newsService.getNews(
            limit = params.loadSize,
            start = if (params is LoadParams.Append) params.key else null
        )

        val newsDto: List<NewsDto> = Converter.newsModelToDto(news)

        LoadResult.Page(
            data = newsDto,
            prevKey = null,
            nextKey = if (news.isEmpty()) null else params.loadSize + NETWORK_PAGE_SIZE
        )
    } catch (e: IOException) {
        LoadResult.Error(e)
    } catch (e: HttpException) {
        LoadResult.Error(e)
    }

    override val keyReuseSupported: Boolean
        get() = true

    override fun getRefreshKey(state: PagingState<Int, NewsDto>): Int?  = state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
}