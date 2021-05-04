package com.highestaim.newsapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorites")
data class FavoriteEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "summery")
    val summery: String?,

    @ColumnInfo(name = "image_url")
    val imageUrl: String?
)