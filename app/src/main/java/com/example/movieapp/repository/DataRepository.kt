package com.example.movieapp.repository

import com.example.movieapp.model.Movie

interface DataRepository {
    fun selectMovie(id: Int)
    fun setMovies(movies: List<Movie>)
    fun getMovies(genre: String?): List<Movie>
    fun getSelectedMovie(): Movie?
}