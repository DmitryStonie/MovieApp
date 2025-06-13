package com.example.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.RecyclerviewMoviesBinding
import com.example.movieapp.movierecyclerview.RecyclerAdapter
import com.example.movieapp.movierecyclerview.items.MovieItem
import com.example.movieapp.ui.MovieCardFragment.Companion.MOVIE_CARD_FRAGMENT
import com.example.movieapp.viewmodels.MovieListViewModel
import kotlin.getValue

class MovieCardsFragment : Fragment() {
    private lateinit var binding: RecyclerviewMoviesBinding
    val viewModel by viewModels<MovieListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = RecyclerviewMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RecyclerAdapter(mutableListOf()) {
            parentFragmentManager.beginTransaction().apply {
                add(R.id.fragment_container_view, MovieCardFragment())
                addToBackStack(MOVIE_CARD_FRAGMENT)
            }.commit()
        }
        binding.movieRecycler.layoutManager = LinearLayoutManager(context)
        binding.movieRecycler.adapter = adapter
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            val items = movies.map { movie -> MovieItem(movie) }
            adapter.updateItems(
                items
            )
        }
    }
}