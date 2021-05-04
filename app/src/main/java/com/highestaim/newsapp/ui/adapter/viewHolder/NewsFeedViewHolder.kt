package com.highestaim.newsapp.ui.adapter.viewHolder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.highestaim.newsapp.R
import com.highestaim.newsapp.databinding.NewsFeedItemBinding
import com.highestaim.newsapp.dto.NewsDto

class NewsFeedViewHolder(private val binding : NewsFeedItemBinding) : RecyclerView.ViewHolder(binding.root) {

    var onFavoriteClick: ((NewsDto) -> Unit)? = null

    fun bind(newsDto : NewsDto) {
        binding.summary.text = newsDto.summery
        binding.title.text = newsDto.title
        binding.newsImage.load(
            newsDto.image
        ) {
            crossfade(true)
        }
        initFavorite(newsDto)
        binding.favoriteImage.setOnClickListener {
            newsDto.isFavorite = !newsDto.isFavorite
            initFavorite(newsDto)
            onFavoriteClick?.invoke(newsDto)
        }
    }


    private fun initFavorite(newsDto : NewsDto){
        val context = binding.favoriteImage.context
        if (newsDto.isFavorite) {
            binding.favoriteImage.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_favorite_filled))
        } else {
            binding.favoriteImage.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_favorite))
        }
    }
}