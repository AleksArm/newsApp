package com.highestaim.newsapp.data.remote.model

data class NewsModel(
    val events: List<Event>,
    val featured: Boolean,
    val id: String,
    val imageUrl: String,
    val launches: List<Any>,
    val newsSite: String,
    val publishedAt: String,
    val summary: String,
    val title: String,
    val updatedAt: String,
    val url: String
)