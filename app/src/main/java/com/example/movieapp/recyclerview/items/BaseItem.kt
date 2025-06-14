package com.example.movieapp.recyclerview.items

sealed interface BaseItem {
    enum class Type(val value: Int) {
        TitleItem(0), TextItem(1), MoviesItem(2),
    }

    val type: Int
}