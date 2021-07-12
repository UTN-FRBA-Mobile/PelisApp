package com.pelisapp.core

interface FirebaseMoviesListener {
    fun onFirebaseMoviesReceived(movies: List<FirebaseMovie>?)
}