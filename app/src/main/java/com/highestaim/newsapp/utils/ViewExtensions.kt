package com.highestaim.newsapp.utils

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.showIf(condition: Boolean) = if (condition) show() else hide()

fun <R> RecyclerView.initRecyclerView(
    context: Context?,
    adapter: R,
    isVertical: Boolean = true,
    isSizeFixed: Boolean = true,
    isNestedScrollingEnabled: Boolean = false
) {
    context?.let {
        this.layoutManager = LinearLayoutManager(
            context,
            if (isVertical) LinearLayoutManager.VERTICAL else LinearLayoutManager.HORIZONTAL,
            false
        )
        this.adapter = adapter as RecyclerView.Adapter<*>
        this.setHasFixedSize(isSizeFixed)
        this.isNestedScrollingEnabled = isNestedScrollingEnabled
    }
}