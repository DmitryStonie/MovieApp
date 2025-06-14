package com.example.movieapp.repository

import com.example.movieapp.model.Movie

interface RemoteMovieRepository {
    suspend fun getMovies(): List<Movie>?
}