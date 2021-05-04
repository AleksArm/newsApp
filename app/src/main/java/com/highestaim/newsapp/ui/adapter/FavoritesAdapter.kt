package com.highestaim.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.highestaim.newsapp.databinding.FavoriteNewsItemBinding
import com.highestaim.newsapp.databinding.NewsFeedItemBinding
import com.highestaim.newsapp.dto.NewsDto
import com.highestaim.newsapp.ui.adapter.viewHolder.FavoritesViewHolder
import com.highestaim.newsapp.ui.adapter.viewHolder.NewsFeedViewHolder

class FavoritesAdapter : ListAdapter<NewsDto,FavoritesViewHolder> (
    FAVORITES_COMPARATOR
){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding = FavoriteNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



    companion object {
        val FAVORITES_COMPARATOR = object : DiffUtil.ItemCallback<NewsDto>() {
            override fun areContentsTheSame(oldItem: NewsDto, newItem: NewsDto): Boolean =
                oldItem.id == newItem.id

            override fun areItemsTheSame(oldItem: NewsDto, newItem: NewsDto): Boolean =
                oldItem.title == newItem.title
        }
    }


}