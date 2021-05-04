package com.highestaim.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.highestaim.newsapp.data.local.dao.FavoriteDao
import com.highestaim.newsapp.data.local.entity.FavoriteEntity


@Database(entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase  : RoomDatabase() {

     abstract fun favoriteDao(): FavoriteDao
}