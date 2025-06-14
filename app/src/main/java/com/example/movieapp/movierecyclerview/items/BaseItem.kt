package com.example.movieapp.movierecyclerview.items

sealed interface BaseItem {
    enum class Type(val value: Int) {
        MovieItem(0),
    }

    val type: Int
}