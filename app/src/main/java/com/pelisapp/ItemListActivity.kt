package com.pelisapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.android.example.pelisApp.SimpleItemRecyclerViewAdapter
import com.pelisapp.core.*
import java.util.logging.Level.INFO

class ItemListActivity : SearchView.OnQueryTextListener, SearchView.OnCloseListener, AppCompatActivity() {
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

        //setupRecyclerView(findViewById(R.id.item_list))
        getUserMovies()
    }

    private fun setUpBotonBusqueda() {
        busqueda = findViewById(R.id.busqueda)
        busqueda.setOnQueryTextListener(this)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, movies: List<Movie>) {
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

    override fun onClose(): Boolean {
        this.getUserMovies()
        return false
    }

    private fun getUserMovies() {
        MovieApi().getAllMoviesFromFirebase(object: MoviesListener {
            override fun onMoviesReceived(savedMovies: List<UserMovie>?) {
                if(savedMovies!!.isEmpty()) {
                    Log.i("ItemList", "No hay peliculas guardadas para el usuario")
                    // TODO aca mostrar un mensajito de que esta vacia, que busque una y la agregue a su lista de favoritas o vistas.
                    return
                }

                var movies = mutableListOf<Movie>()
                savedMovies.forEach {
                    var movie = MovieApi().getMovieByIMDBId(it.imdbID!!) // FIXME pasarle a la Movie si esta fav o no y si esta vista o no antes de pasarsela al recycler
                    if (it.favoriteada!!) {
                        movie.cambiarEstadoFavoriteado()
                    }
                    if (it.vista!!) {
                        movie.cambiarEstadoVista()
                    }
                    movies.add(movie)
                }

                setupRecyclerView(findViewById(R.id.item_list), movies)
            }
        })
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

}