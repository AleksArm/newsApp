package com.highestaim.newsapp.dto

data class NewsDto (
    val id : String,
    val title : String?,
    val summery : String?,
    val image : String?,
    var isFavorite: Boolean = false
    )