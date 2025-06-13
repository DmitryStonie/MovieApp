package com.example.movieapp.movierecyclerview.viewholders

import androidx.core.content.res.ResourcesCompat
import com.example.movieapp.R
import com.example.movieapp.databinding.RecyclerviewMovieItemBinding
import com.example.movieapp.movierecyclerview.items.MovieItem
import com.squareup.picasso.Picasso

class MovieViewHolder(
    private val binding: RecyclerviewMovieItemBinding
) :
    BaseViewHolder(binding.root) {

    fun bind(item: MovieItem) {
        binding.name = item.movie.localizedName
        Picasso.get().load(item.movie.imageUrl).error(
            ResourcesCompat.getDrawable(
                binding.root.context.resources,
                R.drawable.poster_unavailable,
                null
            )!!
        ).placeholder(
            ResourcesCompat.getDrawable(
                binding.root.context.resources,
                R.drawable.poster_unavailable,
                null
            )!!
        ).into(binding.moviePoster)
    }

}