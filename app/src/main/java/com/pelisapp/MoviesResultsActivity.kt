package com.pelisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.core.Movie
import com.pelisapp.core.MovieApi
import com.pelisapp.core.MoviesListener
import com.pelisapp.ui.dashboard.MoviesAdapter

class MoviesResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movies_results)

        val rvMovies = findViewById<View>(R.id.results_recycler_view) as RecyclerView

        rvMovies.layoutManager = LinearLayoutManager(this)

        MovieApi().getAllMoviesFromFirebase(object: MoviesListener{
            override fun onMoviesReceived(movies: List<Movie>?) {
                val adapter = MoviesAdapter(movies)

                rvMovies.adapter = adapter
            }
        })
    }
}