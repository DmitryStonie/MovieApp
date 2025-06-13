package com.example.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.movieapp.databinding.FragmentMovieScreenBinding
import com.example.movieapp.viewmodels.MovieViewModel

class MovieCardFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieScreenBinding.inflate(layoutInflater, container, false)
        val viewModel by requireActivity().viewModels<MovieViewModel>()
        binding.viewmodel = viewModel
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