package com.example.movieapp.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapp.recyclerview.items.BaseItem
import com.example.movieapp.recyclerview.items.MoviesItem
import com.example.movieapp.recyclerview.items.TextItem
import com.example.movieapp.recyclerview.items.TitleItem

class DiffUtilCallback(private val oldItems: List<BaseItem>, private val newItems: List<BaseItem>): DiffUtil.Callback() {
    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem: BaseItem = oldItems[oldItemPosition]
        val newItem: BaseItem = newItems[newItemPosition]
        return when{
            oldItem is TextItem && newItem is TextItem -> {
                oldItem.text == newItem.text
            }
            oldItem is TitleItem && newItem is TitleItem -> {
                oldItem.title == newItem.title
            }
            oldItem is MoviesItem && newItem is MoviesItem -> {
                oldItem.movies == newItem.movies
            }
            else -> false
        }
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}