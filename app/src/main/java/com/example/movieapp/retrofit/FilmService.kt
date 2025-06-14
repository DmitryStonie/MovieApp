package com.example.movieapp.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface FilmService {
    @GET("sequeniatesttask/films.json")
    suspend fun getFilms(): Response<ServiceResponse>
}