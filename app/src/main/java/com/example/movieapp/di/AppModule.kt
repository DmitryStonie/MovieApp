package com.example.movieapp.di

import com.example.movieapp.repository.MovieRepository
import com.example.movieapp.repository.MovieRepositoryImpl
import com.example.movieapp.repository.RemoteMovieRepository
import com.example.movieapp.repository.RemoteMovieRepositoryImpl
import com.example.movieapp.repository.UserSelectionsRepository
import com.example.movieapp.repository.UserSelectionsRepositoryImpl
import com.example.movieapp.retrofit.FilmService
import com.example.movieapp.viewmodels.MovieListViewModel
import com.example.movieapp.viewmodels.MovieViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    singleOf(::MovieRepositoryImpl) { bind<MovieRepository>() }
    singleOf(::RemoteMovieRepositoryImpl) { bind<RemoteMovieRepository>() }
    singleOf(::UserSelectionsRepositoryImpl) { bind<UserSelectionsRepository>() }
    viewModelOf(::MovieListViewModel)
    viewModelOf(::MovieViewModel)
    factory { provideRetrofit() }
    single { provideFilmService(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://s3-eu-west-1.amazonaws.com/").build()

}

fun provideFilmService(retrofit: Retrofit): FilmService = retrofit.create(FilmService::class.java)