package com.example.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapp.viewmodels.MovieListViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieListScreenBinding
import com.example.movieapp.recyclerview.RecyclerAdapter
import com.example.movieapp.recyclerview.items.BaseItem
import com.example.movieapp.recyclerview.items.MoviesItem
import com.example.movieapp.recyclerview.items.TextItem
import com.example.movieapp.recyclerview.items.TitleItem
import com.example.movieapp.recyclerview.viewholders.TextViewHolder
import com.example.movieapp.ui.MovieCardFragment.Companion.MOVIE_CARD_FRAGMENT
import com.google.android.material.snackbar.Snackbar
import android.util.Log
import com.example.movieapp.movierecyclerview.items.MovieItem
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieListScreenBinding
    val viewModel: MovieListViewModel by viewModel()

    private lateinit var genresTitleItem: TitleItem
    private lateinit var moviesTitleItem: TitleItem
    private var genreTextItems: ArrayList<TextItem>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        genresTitleItem = TitleItem(resources.getString(R.string.genres_title))
        moviesTitleItem = TitleItem(resources.getString(R.string.movies_title))

        val adapter = RecyclerAdapter(
            mutableListOf()
        )
        adapter.setOnMovieItemClickListener { movieItem: MovieItem ->
            viewModel.selectMovie(movieItem.movie.id)
            parentFragmentManager.beginTransaction().apply {
                add(R.id.fragment_container_view, MovieCardFragment())
                addToBackStack(MOVIE_CARD_FRAGMENT)
            }.commit()
        }
        adapter.setOnTextItemClickListener { position: Int, holder: TextViewHolder ->
            if (viewModel.selectedGenrePosition == null) {
                val textItem = TextItem(
                    (adapter.items[position] as TextItem).text,
                    resources.getColor(R.color.yellow, null)
                )
                genreTextItems?.set(position - 1, textItem)
                adapter.items[position] = textItem
                viewModel.selectedGenrePosition = position
                adapter.notifyItemChanged(position)
                viewModel.getMoviesByGenre((adapter.items[position] as TextItem).text)
            } else if (viewModel.selectedGenrePosition == position) {
                val textItem = TextItem(
                    (adapter.items[position] as TextItem).text,
                    resources.getColor(R.color.white, null)
                )
                genreTextItems?.set(position - 1, textItem)
                adapter.items[position] = textItem
                viewModel.selectedGenrePosition = null
                adapter.notifyItemChanged(position)
                viewModel.getMoviesByGenre(null)
            } else {
                val ntextItem = TextItem(
                    (adapter.items[viewModel.selectedGenrePosition!!] as TextItem).text,
                    resources.getColor(R.color.white, null)
                )
                adapter.items[viewModel.selectedGenrePosition!!] = ntextItem
                genreTextItems?.set(viewModel.selectedGenrePosition!! - 1, ntextItem)
                adapter.notifyItemChanged(viewModel.selectedGenrePosition!!)
                val textItem = TextItem(
                    (adapter.items[position] as TextItem).text,
                    resources.getColor(R.color.yellow, null)
                )
                genreTextItems?.set(position - 1, textItem)
                adapter.items[position] = textItem
                viewModel.selectedGenrePosition = position
                adapter.notifyItemChanged(position)
                viewModel.getMoviesByGenre(textItem.text)
            }
        }

        binding.movieList.layoutManager = LinearLayoutManager(context)
        binding.movieList.adapter = adapter
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            binding.loadingIndicator.hide()
            if (movies == null) {
                showSnackbar()
            } else {
                val items = arrayListOf<BaseItem>()
                if (genreTextItems == null) {
                    genreTextItems = ArrayList<TextItem>()
                    if (viewModel.genres.value != null) {
                        genreTextItems = ArrayList<TextItem>()
                        viewModel.genres.value!!.forEach { genre ->
                            genreTextItems!!.add(
                                TextItem(
                                    genre,
                                    resources.getColor(R.color.white, null)
                                )
                            )
                        }
                    }
                }
                items.apply {
                    add(genresTitleItem)
                    addAll(genreTextItems!!)
                    add(moviesTitleItem)
                    add(MoviesItem(movies))
                }
                adapter.updateItems(
                    items
                )
            }
        }
        viewModel.getMovies()
    }

    private fun showSnackbar() {
        Snackbar.make(
            binding.coordinator,
            resources.getString(R.string.internet_error_message),
            3000
        ).setAction(resources.getString(R.string.try_again_button_text)) {
            Log.d("INFO", "Got click")
            viewModel.getMovies()
            binding.loadingIndicator.show()
        }.setActionTextColor(resources.getColor(R.color.yellow, null))
            .show()
    }
}