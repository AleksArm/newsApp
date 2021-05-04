package com.highestaim.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.highestaim.newsapp.databinding.NewsFeedItemBinding
import com.highestaim.newsapp.dto.NewsDto
import com.highestaim.newsapp.ui.adapter.viewHolder.NewsFeedViewHolder

class NewsFeedAdapter :  PagingDataAdapter<NewsDto, NewsFeedViewHolder>(NEWS_COMPARATOR){

    var onFavoriteClick: ((NewsDto) -> Unit)? = null

    override fun onBindViewHolder(holder: NewsFeedViewHolder, position: Int) {
        getItem(position)?.run { holder.bind(this) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedViewHolder {
        val binding = NewsFeedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsFeedViewHolder(binding)
    }

    override fun onViewAttachedToWindow(holder: NewsFeedViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.onFavoriteClick = {
            getItem(holder.absoluteAdapterPosition)?.run {  onFavoriteClick?.invoke(this) }
        }
    }

    companion object {
        val NEWS_COMPARATOR = object : DiffUtil.ItemCallback<NewsDto>() {
            override fun areContentsTheSame(oldItem: NewsDto, newItem: NewsDto): Boolean =
                oldItem.id == newItem.id

            override fun areItemsTheSame(oldItem: NewsDto, newItem: NewsDto): Boolean =
                oldItem.title == newItem.title && oldItem.isFavorite == newItem.isFavorite
        }
    }


}