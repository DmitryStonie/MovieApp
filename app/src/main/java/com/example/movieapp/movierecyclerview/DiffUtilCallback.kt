package com.example.movieapp.movierecyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapp.movierecyclerview.items.BaseItem
import com.example.movieapp.movierecyclerview.items.MovieItem

class DiffUtilCallback(private val oldItems: List<BaseItem>, private val newItems: List<BaseItem>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldItems.size
    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(
        oldItemPosition: Int, newItemPosition: Int
    ): Boolean {
        val oldItem: BaseItem = oldItems[oldItemPosition]
        val newItem: BaseItem = newItems[newItemPosition]
        return when {
            oldItem is MovieItem && newItem is MovieItem -> {
                oldItem.movie.id == newItem.movie.id
            }

            else -> false
        }
    }

    override fun areContentsTheSame(
        oldItemPosition: Int, newItemPosition: Int
    ): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}