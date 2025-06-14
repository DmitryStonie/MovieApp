package com.example.movieapp.movierecyclerview.items

import com.example.movieapp.model.Movie

data class MovieItem(val movie: Movie) : BaseItem {
    override val type: Int
        get() = BaseItem.Type.MovieItem.value
}