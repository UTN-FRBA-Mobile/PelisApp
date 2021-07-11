package com.pelisapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.android.example.pelisApp.SimpleItemRecyclerViewAdapter
import com.pelisapp.core.DummyContent
import com.pelisapp.core.MovieApi
import java.util.logging.Level.INFO

class ItemListActivity : SearchView.OnQueryTextListener, AppCompatActivity() {
    private lateinit var adapter: SimpleItemRecyclerViewAdapter
    private val movieApi = MovieApi()
    private lateinit var busqueda: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        setUpBotonBusqueda()

        setupRecyclerView(findViewById(R.id.item_list))
    }

    private fun setUpBotonBusqueda() {
        busqueda = findViewById(R.id.busqueda)
        busqueda.setOnQueryTextListener(this)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        var movies = movieApi.getMovies()
        adapter = SimpleItemRecyclerViewAdapter(movies)
        recyclerView.adapter = adapter
    }

    override fun onQueryTextSubmit(newText: String?): Boolean {
        var queryResult = movieApi.getMoviesWithTitleMatch(newText.orEmpty()) //FIXME no se si habria que checkear que no este empty eh
        if (queryResult.isEmpty()) {
            Log.i("ItemList", "Sin resultados para la busqueda {$title}")
            return false
        }

        adapter.updateDataset(queryResult)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

}