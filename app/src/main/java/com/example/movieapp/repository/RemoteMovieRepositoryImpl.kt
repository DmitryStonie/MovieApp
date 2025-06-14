package com.example.movieapp.repository

import com.example.movieapp.model.Movie
import com.example.movieapp.retrofit.FilmService
import com.example.movieapp.util.toMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteMovieRepositoryImpl(private val filmService: FilmService) : RemoteMovieRepository {
    override suspend fun getMovies(): List<Movie>? {
        return withContext(Dispatchers.IO) {
            val result = filmService.getFilms()
            val movies = result.body()?.films?.map { film -> film.toMovie() } ?: listOf()
            return@withContext movies
        }
    }
}