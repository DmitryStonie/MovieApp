package com.example.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapp.viewmodels.MovieListViewModel
import androidx.fragment.app.viewModels
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


class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieListScreenBinding
    val viewModel by viewModels<MovieListViewModel>()

    lateinit private var genresTitleItem: TitleItem
    lateinit private var moviesTitleItem: TitleItem

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
            mutableListOf())
        adapter.setOnMovieItemClickListener {
            parentFragmentManager.beginTransaction().apply {
                add(R.id.fragment_container_view, MovieCardFragment())
                addToBackStack(MOVIE_CARD_FRAGMENT)
            }.commit()
        }
        adapter.setOnTextItemClickListener { position: Int, holder: TextViewHolder ->
                if(viewModel.selectedGenrePosition == null){
                    adapter.items[position] = TextItem((adapter.items[position] as TextItem).text, resources.getColor(R.color.yellow, null))
                    viewModel.selectedGenrePosition = position
                    adapter.notifyItemChanged(position)
                    //reload data
                } else if (viewModel.selectedGenrePosition == position) {
                    adapter.items[position] = TextItem((adapter.items[position] as TextItem).text, resources.getColor(R.color.white, null))
                    viewModel.selectedGenrePosition = null
                    adapter.notifyItemChanged(position)
                    //reload data
                } else {
                    adapter.items[viewModel.selectedGenrePosition!!] = TextItem((adapter.items[viewModel.selectedGenrePosition!!] as TextItem).text, resources.getColor(R.color.white, null))
                    adapter.notifyItemChanged(viewModel.selectedGenrePosition!!)
                    adapter.items[position] = TextItem((adapter.items[position] as TextItem).text, resources.getColor(R.color.yellow, null))
                    viewModel.selectedGenrePosition = position
                    adapter.notifyItemChanged(position)
                    //reload data
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
                items.apply {
                    add(genresTitleItem)
                    if (viewModel.genres.value != null) {
                        addAll(viewModel.genres.value!!.map { genre -> TextItem(genre, resources.getColor(R.color.white, null)) })
                    }
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