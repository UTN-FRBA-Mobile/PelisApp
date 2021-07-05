package com.pelisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.core.*
import com.pelisapp.ui.dashboard.MoviesAdapter

class MoviesResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movies_results)

        val rvMovies = findViewById<View>(R.id.results_recycler_view) as RecyclerView

        rvMovies.layoutManager = LinearLayoutManager(this)

        MovieApi().getAllMoviesFromFirebase(object: MoviesListener{
            override fun onMoviesReceived(movies: List<Movie>?) {
                PreferencesApi().getAllPreferencesFromFirebase(object: PreferencesListener {
                    override fun onPreferencesReceived(preferences: HashMap<String, Preference>?) {
                        var directors = preferences!!.values.toList().map { pref -> pref.director }
                        var genres = preferences!!.values.toList().map { pref -> pref.genre }

                        var chosenDirector = getMostRepeatedWord(directors)
                        var chosenGenre = getMostRepeatedWord(genres)

                        var chosenMovies = movies!!.filter { movie -> movie.director.equals(chosenDirector) && movie.genre.equals(chosenGenre) }

                        val adapter = MoviesAdapter(chosenMovies)

                        rvMovies.adapter = adapter
                    }
                })
            }
        })
    }

    fun getMostRepeatedWord(words: List<String?>): String? {
        return words.groupingBy{ it }.eachCount().entries.maxByOrNull{ (k, v) -> v }!!.key
    }
}