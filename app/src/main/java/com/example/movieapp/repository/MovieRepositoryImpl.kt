package com.example.movieapp.repository

import com.example.movieapp.model.Movie


class MovieRepositoryImpl : MovieRepository {
    private var movies: HashMap<Int, Movie>? = null
    private var genres: HashSet<String>? = null

    override fun setMovies(movies: List<Movie>) {
        this.movies = HashMap<Int, Movie>()
        this.genres = HashSet<String>()
        movies.forEach { movie ->
            this.movies?.put(movie.id, movie)
            movie.genres?.forEach { genre -> genres!!.add(genre) }
        }
    }

    override fun getMovies(genre: String?): List<Movie>? {
        val result = arrayListOf<Movie>()
        if (genre == null) {
            movies?.forEach { id, movie -> result.add(movie) }
        } else {
            movies?.forEach { id, movie ->
                if (movie.genres?.contains(genre) == true) result.add(
                    movie
                )
            }
        }
        return if (result.isEmpty()) null else result.sortedBy { movie -> movie.localizedName }
    }



    override fun getMovieById(id: Int): Movie? {
        return movies?.get(id)
    }

    override fun getGenres(): List<String> {
        val result = ArrayList<String>()
        genres?.forEach { genre -> result.add(genre) }
        return result.sorted()
    }


}

