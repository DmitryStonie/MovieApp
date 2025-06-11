package com.example.movieapp.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET("sequeniatesttask/films.json")
    suspend fun getMovies() : Response<ServiceResponse>
}