package com.example.movieapp.repository

import com.example.movieapp.model.Movie

class UserSelectionsRepositoryImpl : UserSelectionsRepository {
    private var selectedMovie: Movie? = null
    private var selectedGenre: String? = null

    override fun selectMovie(movie: Movie?) {
        selectedMovie = movie
    }

    override fun getSelectedMovie(): Movie? {
        return selectedMovie
    }

    override fun selectGenre(genre: String?) {
        selectedGenre = genre
    }

    override fun getSelectedGenre(): String? {
        return selectedGenre
    }
}