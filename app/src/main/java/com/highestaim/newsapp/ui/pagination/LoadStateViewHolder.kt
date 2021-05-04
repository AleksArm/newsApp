package com.highestaim.newsapp.ui.pagination

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.LoadState
import androidx.paging.LoadState.Loading
import androidx.recyclerview.widget.RecyclerView
import com.highestaim.newsapp.R
import com.highestaim.newsapp.utils.showIf


class LoadStateViewHolder(
    parent: ViewGroup,
    retry: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.pagination_footer_item, parent, false)
) {

    init {
        itemView.findViewById<AppCompatTextView>(R.id.retry).setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        val retry = itemView.findViewById<AppCompatTextView>(R.id.retry)
        val moreProgressBar = itemView.findViewById<ProgressBar>(R.id.load_more)
        moreProgressBar.showIf(loadState is Loading)

        if (loadState !is Loading) {
            retry.showIf(loadState is LoadState.Error)
        }

    }
}