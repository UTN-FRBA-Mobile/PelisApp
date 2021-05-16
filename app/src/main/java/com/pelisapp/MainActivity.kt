package com.pelisapp

import RecyclerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.core.Movie

class MainActivity : AppCompatActivity() {
    lateinit var moviesRecyclerView : RecyclerView
    val moviesAdapter : RecyclerAdapter = RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
    }

    fun setUpRecyclerView(){
        moviesRecyclerView = findViewById(R.id.rvPelis) as RecyclerView
        moviesRecyclerView.setHasFixedSize(true)
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        moviesAdapter.RecyclerAdapter(getPelis(), this)
        moviesRecyclerView.adapter = moviesAdapter
    }

    fun getPelis(): MutableList<Movie>{
        var movies:MutableList<Movie> = ArrayList()
        movies.add(Movie("Spiderman", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg", "4.5", "ESP/ENG"))
        movies.add(Movie("Daredevil", "https://cursokotlin.com/wp-content/uploads/2017/07/daredevil.jpg", "5.0", "ESP/ENG/JPN"))
        return movies
    }
}