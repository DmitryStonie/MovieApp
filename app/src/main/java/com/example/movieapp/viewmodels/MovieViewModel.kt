package com.example.movieapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.Movie
import java.util.Locale

class MovieViewModel: ViewModel() {
    private var movie = MutableLiveData(Movie(1, "asf", "sdf", 1999, 4.5F, "as", "safads", listOf("sfads", "dsage")))

    val name = movie.value?.name?.split(" ")?.map { it.replaceFirstChar(Char::titlecase) }?.joinToString(" ")
    val localizedName = movie.value?.name
    val rating = String.format(Locale.UK, "%1$,.1f", movie.value?.rating)
    val description = movie.value?.description
    val movieInfo = "${movie.value?.genres?.joinToString(", ")}, ${movie.value?.year}"
}