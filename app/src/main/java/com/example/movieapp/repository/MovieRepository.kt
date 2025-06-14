package com.example.movieapp.repository

import com.example.movieapp.model.Movie

interface MovieRepository {
    fun setMovies(movies: List<Movie>)
    fun getMovies(genre: String?): List<Movie>?
    fun getMovieById(id: Int): Movie?
    fun getGenres(): List<String>
}