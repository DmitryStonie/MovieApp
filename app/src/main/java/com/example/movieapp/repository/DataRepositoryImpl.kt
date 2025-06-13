package com.example.movieapp.repository

import com.example.movieapp.model.Movie


class DataRepositoryImpl : DataRepository {
    private var movies: HashMap<Int, Movie>? = null
    private var selectedMovie: Movie? = null
    override fun selectMovie(id: Int) {
        selectedMovie = movies?.get(id)
    }

    override fun setMovies(movies: List<Movie>) {
        this.movies = HashMap<Int, Movie>()
        movies.forEach { movie -> this.movies?.put(movie.id, movie) }
    }

    override fun getMovies(genre: String?): List<Movie> {
        val result = arrayListOf<Movie>()
        if(genre == null){
            movies?.forEach { id, movie -> result.add(movie) }
            return result
        } else{
            movies?.forEach { id, movie -> if(movie.genres?.contains(genre) == true) result.add(movie) }
        }
        return result
    }

    override fun getSelectedMovie(): Movie? {
        return selectedMovie
    }


}

