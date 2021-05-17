package com.pelisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.core.MovieApi
import com.pelisapp.ui.dashboard.MoviesAdapter

class MoviesResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movies_results)

        val rvMovies = findViewById<View>(R.id.results_recycler_view) as RecyclerView

        //ToDo: reemplazar por peliculas elegidas
        val movies = MovieApi().getMovies()

        val adapter = MoviesAdapter(movies)

        rvMovies.adapter = adapter

        rvMovies.layoutManager = LinearLayoutManager(this)
    }
}