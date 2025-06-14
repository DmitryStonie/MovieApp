package com.example.movieapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.UserSelectionsRepository
import java.util.Locale

class MovieViewModel(val repository: UserSelectionsRepository) : ViewModel() {
    private var movie: Movie? = null
    fun getSelectedMovie() {
        val result = repository.getSelectedMovie()
        if (result != null) {
            movie = result
        }
    }

    val name: String
        get() = movie?.name ?: ""
    val localizedName: String get() = movie?.localizedName ?: ""
    val rating: String?
        get() = if (movie?.rating != null) {
            String.format(Locale.UK, "%1$,.1f", movie?.rating)
        } else {
            "-"
        }
    val description: String? get() = movie?.description ?: ""
    val movieInfo: String?
        get() = "${movie?.genres?.joinToString(", ")}${if (movie?.genres == null || movie?.genres?.size == 0) "" else ", "}${movie?.year}"
    val imageUrl: String? get() = movie?.imageUrl
}