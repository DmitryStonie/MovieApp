package com.example.movieapp.ui

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.viewmodels.MovieListViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieListScreenBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.movierecyclerview.MovieCardItemDecoration
import com.example.movieapp.movierecyclerview.RecyclerAdapter as MovieRecyclerAdapter
import com.example.movieapp.recyclerview.RecyclerAdapter
import com.example.movieapp.recyclerview.items.BaseItem
import com.example.movieapp.recyclerview.items.MoviesItem
import com.example.movieapp.recyclerview.items.TextItem
import com.example.movieapp.recyclerview.items.TitleItem
import com.example.movieapp.ui.MovieCardFragment.Companion.MOVIE_CARD_FRAGMENT
import com.google.android.material.snackbar.Snackbar
import com.example.movieapp.movierecyclerview.items.MovieItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieListScreenBinding
    val viewModel: MovieListViewModel by viewModel()

    private lateinit var genresTitleItem: TitleItem
    private lateinit var moviesTitleItem: TitleItem
    private lateinit var moviesItem: MoviesItem
    private var genreTextItems: ArrayList<TextItem>? = null

    private var items: ArrayList<BaseItem>? = null
    private lateinit var movieAdapter: MovieRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        genresTitleItem = TitleItem(resources.getString(R.string.genres_title))
        moviesTitleItem = TitleItem(resources.getString(R.string.movies_title))

        val adapter = RecyclerAdapter()
        adapter.onGenreClick = { position: Int ->
            val genre = (adapter.items[position] as TextItem).text
            if (viewModel.selectedGenre == null) {
                updateGenreItemColor(genre, R.color.yellow)
                adapter.notifyItemChanged(position)
                viewModel.selectGenre(genre)
            } else if (viewModel.selectedGenre == genre) {
                updateGenreItemColor(genre, R.color.white)
                adapter.notifyItemChanged(position)
                viewModel.selectGenre(null)
            } else {
                updateGenreItemColor(
                    viewModel.selectedGenre!!, R.color.white
                )
                updateGenreItemColor(genre, R.color.yellow)
                adapter.notifyItemChanged(getPosition(viewModel.selectedGenre!!))
                adapter.notifyItemChanged(position)
                viewModel.selectGenre(genre)
            }
        }

        binding.movieList.layoutManager = LinearLayoutManager(context)
        binding.movieList.adapter = adapter
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            binding.loadingIndicator.hide()
            if (movies == null) {
                showSnackbar()
            } else {
                if (items == null) {
                    items = arrayListOf<BaseItem>()
                    initGenreItems()
                    items!!.apply {
                        add(genresTitleItem)
                        addAll(genreTextItems!!)
                        add(moviesTitleItem)
                    }
                    initMoviesItem(movies)
                    items!!.add(moviesItem)
                }
                movieAdapter.updateItems(movies.map { movie -> MovieItem(movie) })
                adapter.updateItems(
                    items!!
                )
            }
        }
        Log.d("INFO", "lm")
        viewModel.loadMovies()
    }

    private fun showSnackbar() {
        Snackbar.make(
            binding.coordinator,
            resources.getString(R.string.internet_error_message),
            SNACKBAR_DURATION
        ).setAction(resources.getString(R.string.try_again_button_text)) {
            binding.loadingIndicator.show()
            viewModel.loadMovies()
        }.setActionTextColor(resources.getColor(R.color.yellow, null)).show()
    }

    private fun initGenreItems() {
        if (genreTextItems == null) {
            genreTextItems = ArrayList<TextItem>()
            if (viewModel.genres.value != null) {
                genreTextItems = ArrayList<TextItem>()
                viewModel.genres.value!!.forEach { genre ->
                    val color =
                        if (genre == viewModel.selectedGenre) R.color.yellow else R.color.white
                    genreTextItems!!.add(
                        TextItem(
                            genre, color
                        )
                    )
                }
            }
        }
    }

    private fun updateGenreItemColor(genre: String, color: Int) {
        if (genreTextItems == null) return
        val position = getPosition(genre)
        if (position == NOT_FOUND) return
        val item = TextItem(
            genre, color
        )
        items!![position] = item
    }

    private fun getPosition(genre: String): Int {
        val index = genreTextItems?.indexOfFirst { textItem -> textItem.text == genre }
        return if (index == -1) NOT_FOUND
        else index!! + GENRES_OFFSET
    }

    private fun initMoviesItem(movies: List<Movie>) {
        movieAdapter = MovieRecyclerAdapter(arrayListOf()) { movieId: Int ->
            viewModel.selectMovie(movieId)
            parentFragmentManager.beginTransaction().apply {
                add(R.id.fragment_container_view, MovieCardFragment())
                addToBackStack(MOVIE_CARD_FRAGMENT)
            }.commit()
        }
        val movieLayoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(binding.root.context, NUM_OF_COLUMNS_LANDSCAPE)
        } else {
            GridLayoutManager(binding.root.context, NUM_OF_COLUMNS_PORTRAIT)
        }
        val movieDecoration = MovieCardItemDecoration(
            binding.root.resources.getDimensionPixelSize(R.dimen.movie_card_vertical_margin),
            binding.root.resources.getDimensionPixelSize(R.dimen.movie_card_horizontal_margin)
        )
        moviesItem = MoviesItem(movies, movieAdapter, movieLayoutManager, movieDecoration)
    }

    companion object {
        const val NUM_OF_COLUMNS_PORTRAIT = 2
        const val NUM_OF_COLUMNS_LANDSCAPE = 4
        const val NOT_FOUND = -1
        const val GENRES_OFFSET = 1
        const val SNACKBAR_DURATION = 3000
    }
}