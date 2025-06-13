package com.example.movieapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.Movie
import com.example.movieapp.repository.DataRepository
import com.example.movieapp.retrofit.FilmService
import com.example.movieapp.util.toMovie
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieListViewModel(val dataRepository: DataRepository): ViewModel() {
    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://s3-eu-west-1.amazonaws.com/").build()
    private val filmService = retrofit.create(FilmService::class.java)
    val genres = MutableLiveData<Set<String>>()
    val movies = MutableLiveData<List<Movie>>()
    var selectedGenrePosition: Int? = null
    fun getMovies(){
        viewModelScope.launch {
            try{
                Log.d("INFO", "try to get")
                var movies = listOf<Movie>()
                var genres = mutableSetOf<String>()
                val result = filmService.getFilms()
                result.body()?.films?.forEach { film -> film.genres?.let{genres.addAll(it)} }
                movies = result.body()?.films?.map { film -> film.toMovie() } ?: listOf()
                dataRepository.setMovies(movies)
                this@MovieListViewModel.genres.postValue(genres)
                this@MovieListViewModel.movies.postValue(movies)
            } catch(e: Exception){
                Log.e("ERROR", "Got exception: ${e.message}")
                this@MovieListViewModel.genres.postValue(null)
                this@MovieListViewModel.movies.postValue(null)
            }
        }
    }
    fun selectMovie(id: Int){
        dataRepository.selectMovie(id)
    }
}