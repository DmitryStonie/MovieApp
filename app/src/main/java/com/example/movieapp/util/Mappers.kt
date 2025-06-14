package com.example.movieapp.util

import com.example.movieapp.model.Movie
import com.example.movieapp.retrofit.Film

fun Film.toMovie(): Movie {
    return Movie(
        id, localizedName, name, year, rating, imageUrl, description, genres
    )
}