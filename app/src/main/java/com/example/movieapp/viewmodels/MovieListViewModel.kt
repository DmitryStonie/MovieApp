package com.example.movieapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.MovieRepository
import com.example.movieapp.repository.UserSelectionsRepository
import com.example.movieapp.retrofit.FilmService
import com.example.movieapp.util.toMovie
import kotlinx.coroutines.launch

class MovieListViewModel(val movieRepository: MovieRepository, val selectionsRepository: UserSelectionsRepository, val filmService: FilmService): ViewModel() {
    private var isRemoteMoviesTriedToLoad = false

    val genres = MutableLiveData<Set<String>>()
    val movies = MutableLiveData<List<Movie>>()

    val selectedGenre: String?
        get() = selectionsRepository.getSelectedGenre()

    private fun getMoviesRemote(){
        viewModelScope.launch {
            try{
                var movies = listOf<Movie>()
                var genres = mutableSetOf<String>()
                val result = filmService.getFilms()
                result.body()?.films?.forEach { film -> film.genres?.let{genres.addAll(it)} }
                movies = result.body()?.films?.map { film -> film.toMovie() } ?: listOf()
                movieRepository.setMovies(movies)
                this@MovieListViewModel.genres.postValue(genres)
                this@MovieListViewModel.movies.postValue(movies)
            } catch(e: Exception){
                Log.e("ERROR", "Got exception: ${e.message}")
                this@MovieListViewModel.genres.postValue(null)
                this@MovieListViewModel.movies.postValue(null)
            } finally {
                isRemoteMoviesTriedToLoad = true
            }
        }
    }

    fun getMovies(){
        if(!isRemoteMoviesTriedToLoad){
            getMoviesRemote()
        } else{
            var movies = movieRepository.getMovies(selectionsRepository.getSelectedGenre())
            var genres = mutableSetOf<String>()
            movies?.forEach { film -> film.genres?.let{genres.addAll(it)} }
            this@MovieListViewModel.genres.value = genres
            this@MovieListViewModel.movies.value = movies
        }
    }


    fun selectMovie(id: Int){
        val movie = movieRepository.getMovieById(id)
        selectionsRepository.selectMovie(movie)
    }

    fun selectGenre(genre: String?){
        this@MovieListViewModel.movies.postValue(movieRepository.getMovies(genre))
        selectionsRepository.selectGenre(genre)
    }
}