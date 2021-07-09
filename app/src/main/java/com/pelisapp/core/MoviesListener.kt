package com.pelisapp.core

interface MoviesListener {
    fun onMoviesReceived(movies: List<Movie>?)
}