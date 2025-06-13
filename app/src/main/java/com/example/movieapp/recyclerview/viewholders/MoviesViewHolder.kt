package com.example.movieapp.recyclerview.viewholders

import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.RecyclerviewMoviesBinding
import com.example.movieapp.movierecyclerview.MovieCardItemDecoration
import com.example.movieapp.movierecyclerview.RecyclerAdapter
import com.example.movieapp.movierecyclerview.items.MovieItem
import com.example.movieapp.recyclerview.items.MoviesItem

class MoviesViewHolder(private val binding: RecyclerviewMoviesBinding, private val onClick: ((movieItem: MovieItem) -> Unit)?) :
    BaseViewHolder(binding.root) {
    fun bind(item: MoviesItem) {
        binding.movieRecycler.layoutManager = GridLayoutManager(binding.root.context, 2)
        val items = item.movies.map { movie -> MovieItem(movie) }
        val adapter = RecyclerAdapter(arrayListOf(), onClick)
        binding.movieRecycler.adapter = adapter
        binding.movieRecycler.addItemDecoration(
            MovieCardItemDecoration(
                binding.root.resources.getDimensionPixelSize(R.dimen.movie_card_vertical_margin),
                binding.root.resources.getDimensionPixelSize(R.dimen.movie_card_horizontal_margin),
                items.size,
                2
            )
        )
        adapter.updateItems(items)
    }
}