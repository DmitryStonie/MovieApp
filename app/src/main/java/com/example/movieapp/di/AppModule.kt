package com.example.movieapp.di

import com.example.movieapp.repository.DataRepository
import com.example.movieapp.repository.DataRepositoryImpl
import com.example.movieapp.viewmodels.MovieListViewModel
import com.example.movieapp.viewmodels.MovieViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::DataRepositoryImpl) {bind<DataRepository>()}
    viewModelOf(::MovieListViewModel)
    viewModelOf(::MovieViewModel)
}