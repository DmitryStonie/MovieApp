package com.example.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapp.databinding.RecyclerviewMoviesBinding

class MovieCardsFragment : Fragment() {
    private lateinit var binding: RecyclerviewMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = RecyclerviewMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

}