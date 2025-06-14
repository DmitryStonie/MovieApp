package com.example.movieapp.movierecyclerview.viewholders

import com.example.movieapp.databinding.RecyclerviewMovieItemBinding
import com.example.movieapp.movierecyclerview.items.MovieItem

class MovieViewHolder(
    private val binding: RecyclerviewMovieItemBinding
) : BaseViewHolder(binding.root) {

    fun bind(item: MovieItem) {
        binding.movie = item.movie
    }

}