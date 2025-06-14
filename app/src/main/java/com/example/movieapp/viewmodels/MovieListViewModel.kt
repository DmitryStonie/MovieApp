package com.example.movieapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.MovieRepository
import com.example.movieapp.repository.RemoteMovieRepository
import com.example.movieapp.repository.UserSelectionsRepository
import kotlinx.coroutines.launch

class MovieListViewModel(
    val movieRepository: MovieRepository,
    val selectionsRepository: UserSelectionsRepository,
    val movieRemoteRepository: RemoteMovieRepository
) : ViewModel() {
    private var isRemoteMoviesLoaded = false

    val genres = MutableLiveData<List<String>>()
    val movies = MutableLiveData<List<Movie>>()

    val selectedGenre: String?
        get() = selectionsRepository.getSelectedGenre()

    private fun getMoviesRemote() {
        viewModelScope.launch {
            try {
                var movies = listOf<Movie>()
                movies = movieRemoteRepository.getMovies() ?: listOf()
                isRemoteMoviesLoaded = true
                movieRepository.setMovies(movies)
                val genres = movieRepository.getGenres()
                movies = movieRepository.getMovies(null) ?: listOf()
                this@MovieListViewModel.genres.postValue(genres)
                this@MovieListViewModel.movies.postValue(movies)
            } catch (e: Exception) {
                Log.d("ERROR", "Got exception ${e.message}")
                this@MovieListViewModel.genres.postValue(null)
                this@MovieListViewModel.movies.postValue(null)
            }
        }
    }

    fun loadMovies() {
        if (!isRemoteMoviesLoaded) {
            getMoviesRemote()
        } else {
            var movies = movieRepository.getMovies(selectionsRepository.getSelectedGenre())
            this@MovieListViewModel.movies.value = movies
        }
    }

    fun selectMovie(id: Int) {
        val movie = movieRepository.getMovieById(id)
        selectionsRepository.selectMovie(movie)
    }

    fun selectGenre(genre: String?) {
        selectionsRepository.selectGenre(genre)
        this@MovieListViewModel.movies.value = movieRepository.getMovies(genre)
    }
}