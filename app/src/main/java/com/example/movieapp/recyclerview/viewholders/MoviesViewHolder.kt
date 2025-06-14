package com.example.movieapp.recyclerview.viewholders

import com.example.movieapp.databinding.RecyclerviewMoviesBinding
import com.example.movieapp.movierecyclerview.items.MovieItem
import com.example.movieapp.recyclerview.items.MoviesItem

class MoviesViewHolder(private val binding: RecyclerviewMoviesBinding) :
    BaseViewHolder(binding.root) {
    fun bind(item: MoviesItem) {
        binding.movieRecycler.layoutManager = item.layoutManager
        binding.movieRecycler.adapter = item.adapter
        if (binding.movieRecycler.itemDecorationCount < 1) {
            binding.movieRecycler.addItemDecoration(
                item.itemDecoration
            )
        }
        val items = item.movies.map { movie -> MovieItem(movie) }
        item.adapter.updateItems(items)
    }
}