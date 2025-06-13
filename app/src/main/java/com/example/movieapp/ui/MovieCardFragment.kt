package com.example.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapp.databinding.FragmentMovieScreenBinding
import com.example.movieapp.viewmodels.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.util.Log

class MovieCardFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieScreenBinding.inflate(layoutInflater, container, false)
        val viewModel: MovieViewModel by viewModel()
        viewModel.getSelectedMovie()
        binding.viewmodel = viewModel
        Log.d("INFO", "viewmodel setted")
        binding.lifecycleOwner = this
        binding.topAppBar.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return binding.root
    }
    companion object{
        const val MOVIE_CARD_FRAGMENT="MovieCardFragment"
    }
}