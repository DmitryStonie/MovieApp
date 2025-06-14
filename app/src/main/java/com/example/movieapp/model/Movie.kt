package com.example.movieapp.model

data class Movie(
    val id: Int,
    val localizedName: String?,
    val name: String?,
    val year: Int?,
    val rating: Float?,
    val imageUrl: String?,
    val description: String?,
    val genres: List<String>?
)