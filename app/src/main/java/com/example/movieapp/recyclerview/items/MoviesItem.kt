package com.example.movieapp.recyclerview.items

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.model.Movie
import com.example.movieapp.movierecyclerview.MovieCardItemDecoration
import com.example.movieapp.movierecyclerview.RecyclerAdapter

data class MoviesItem(val movies: List<Movie> = listOf<Movie>(), val adapter: RecyclerAdapter, val layoutManager: LinearLayoutManager, val itemDecoration: MovieCardItemDecoration): BaseItem {
    override val type: Int
        get() = BaseItem.Type.MoviesItem.value
}