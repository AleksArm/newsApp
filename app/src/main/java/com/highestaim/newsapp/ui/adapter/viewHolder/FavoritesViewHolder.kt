package com.highestaim.newsapp.ui.adapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.highestaim.newsapp.databinding.FavoriteNewsItemBinding
import com.highestaim.newsapp.dto.NewsDto

class FavoritesViewHolder(private val binding: FavoriteNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(newsDto: NewsDto) {
        binding.summary.text = newsDto.summery
        binding.title.text = newsDto.title
        binding.newsImage.load(
            newsDto.image
        ) {
            crossfade(true)
        }
    }
}