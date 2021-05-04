package com.highestaim.newsapp.data.remote.service

import com.highestaim.newsapp.data.remote.model.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/articles")
    suspend fun getNews (@Query("_limit") limit : Int,@Query("_start") start : Int?) : List<NewsModel>
}