package com.example.movieapp.recyclerview.items

import com.example.movieapp.model.Movie

class MoviesItem(val movies: List<Movie>): BaseItem {
    override val type: Int
        get() = BaseItem.Type.MoviesItem.value
}