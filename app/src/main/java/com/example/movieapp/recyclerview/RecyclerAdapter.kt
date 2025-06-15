package com.example.movieapp.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.RecyclerviewMoviesBinding
import com.example.movieapp.databinding.RecyclerviewTextItemBinding
import com.example.movieapp.databinding.RecyclerviewTitleItemBinding
import com.example.movieapp.recyclerview.items.BaseItem
import com.example.movieapp.recyclerview.items.MoviesItem
import com.example.movieapp.recyclerview.items.TextItem
import com.example.movieapp.recyclerview.items.TitleItem
import com.example.movieapp.recyclerview.viewholders.BaseViewHolder
import com.example.movieapp.recyclerview.viewholders.MoviesViewHolder
import com.example.movieapp.recyclerview.viewholders.TextViewHolder
import com.example.movieapp.recyclerview.viewholders.TitleViewHolder

class RecyclerAdapter(var items: MutableList<BaseItem> = mutableListOf<BaseItem>()) :
    RecyclerView.Adapter<BaseViewHolder>() {
    var onGenreClick: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BaseViewHolder {
        val viewHolder = when (viewType) {
            BaseItem.Type.TitleItem.value -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<RecyclerviewTitleItemBinding>(
                    inflater, R.layout.recyclerview_title_item, parent, false
                )
                TitleViewHolder(binding)
            }

            BaseItem.Type.TextItem.value -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<RecyclerviewTextItemBinding>(
                    inflater, R.layout.recyclerview_text_item, parent, false
                )
                TextViewHolder(binding)
            }

            BaseItem.Type.MoviesItem.value -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<RecyclerviewMoviesBinding>(
                    inflater, R.layout.recyclerview_movies, parent, false
                )
                MoviesViewHolder(binding)
            }

            else -> {
                throw IllegalStateException("Illegal item with viewType $viewType")
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
            is TextViewHolder -> {
                holder.bind(items[position] as TextItem)
                if (onGenreClick != null) {
                    holder.itemView.setOnClickListener { onGenreClick!!(position) }
                }
            }

            is TitleViewHolder -> {
                holder.bind(items[position] as TitleItem)
            }

            is MoviesViewHolder -> {
                holder.bind(items[position] as MoviesItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(newItems: List<BaseItem>) {
        val diffUtilCallback = DiffUtilCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

}