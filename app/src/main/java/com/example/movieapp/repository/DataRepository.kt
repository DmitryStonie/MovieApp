package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.movieapp.model.Movie


class DataRepository {
    private var movie: MediatorLiveData<Movie?>? = null

    fun getMovie(): LiveData<Movie?> {
        return movie!!
    }

    fun setMovie(movie: Movie) {
        this.movie?.value = movie
    }

}

