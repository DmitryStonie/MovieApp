package com.example.movieapp.movierecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.RecyclerviewMovieItemBinding
import com.example.movieapp.movierecyclerview.items.BaseItem
import com.example.movieapp.movierecyclerview.items.MovieItem
import com.example.movieapp.movierecyclerview.viewholders.BaseViewHolder
import com.example.movieapp.movierecyclerview.viewholders.MovieViewHolder

class RecyclerAdapter(var items: List<BaseItem>, private val onClick: ((movieId: Int) -> Unit)?) :
    RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BaseViewHolder {
        val viewHolder = when (viewType) {

            BaseItem.Type.MovieItem.value -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<RecyclerviewMovieItemBinding>(
                    inflater, R.layout.recyclerview_movie_item, parent, false
                )
                val holder = MovieViewHolder(binding)
                holder
            }

            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<RecyclerviewMovieItemBinding>(
                    inflater, R.layout.recyclerview_movie_item, parent, false
                )
                val holder = MovieViewHolder(binding)
                holder
            }
        }
        return viewHolder
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder, position: Int
    ) {
        when (holder) {
            is MovieViewHolder -> {
                val item = items[position] as MovieItem
                holder.bind(item)
                holder.itemView.setOnClickListener {
                    onClick?.invoke(item.movie.id)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(newItems: List<BaseItem>) {
        val diffUtilCallback = DiffUtilCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }
}