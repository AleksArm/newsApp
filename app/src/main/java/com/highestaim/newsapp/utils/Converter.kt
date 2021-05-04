package com.highestaim.newsapp.utils

import com.highestaim.newsapp.data.local.entity.FavoriteEntity
import com.highestaim.newsapp.data.remote.model.NewsModel
import com.highestaim.newsapp.dto.NewsDto

object Converter {

    fun newsModelToDto(newsModel : List<NewsModel>): List<NewsDto>  = newsModel.map { news ->
        NewsDto(
            id = news.id,
            title = news.title,
            summery = news.summary,
            image = news.imageUrl
        )
    }


    fun favoriteEntitiesToDto(favoritesModel : List<FavoriteEntity>): List<NewsDto>  = favoritesModel.map { favorite ->
        NewsDto(
            id = favorite.id,
            title = favorite.title,
            summery = favorite.summery,
            image = favorite.imageUrl
        )
    }

    fun favoriteEntityToDto(newsDto : NewsDto): FavoriteEntity  =
        FavoriteEntity(
            id = newsDto.id,
            title = newsDto.title,
            summery = newsDto.summery,
            imageUrl = newsDto.image
        )

    fun favoriteDtoToEntity(favoriteEntity : FavoriteEntity): NewsDto  =
        NewsDto(
            id = favoriteEntity.id,
            title = favoriteEntity.title,
            summery = favoriteEntity.summery,
            image = favoriteEntity.imageUrl
        )



}