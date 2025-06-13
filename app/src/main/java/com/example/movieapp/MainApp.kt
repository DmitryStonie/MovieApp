package com.example.movieapp

import android.app.Application
import com.example.movieapp.repository.DataRepository

class MainApp: Application() {
    private val repository = DataRepository()
    fun getRepository(): DataRepository {
        return repository
    }
}