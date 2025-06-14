package com.example.movieapp.repository

import com.example.movieapp.model.Movie

interface UserSelectionsRepository {
    fun selectMovie(movie: Movie?)
    fun getSelectedMovie(): Movie?
    fun selectGenre(genre: String?)
    fun getSelectedGenre(): String?
}