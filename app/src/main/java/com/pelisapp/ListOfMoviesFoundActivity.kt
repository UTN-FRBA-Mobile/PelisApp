package com.pelisapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.pelisapp.core.MovieFound
import okhttp3.*
import java.io.IOException

class ListOfMoviesFoundActivity : AppCompatActivity() {
    private lateinit var adapter: SimpleItemRecyclerViewAdapterMovieResult
    private val mapper: ObjectMapper = jacksonObjectMapper()
    private val apiKey = "4ea2d82"
    private val jsonMainNodeKey = "Search"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_found_list)
        val movieToFind = intent.getStringExtra("movieToFind")
        callToOmdb(movieToFind!!)
    }

    private fun callToOmdb(movieToFind: String) {
        val url = "https://www.omdbapi.com/?s=$movieToFind&type=movie&apikey=$apiKey";
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) = run {
                val bodyResponse = response.body?.string()
                setupRecyclerView(findViewById(R.id.item_list), bodyResponse)
            }
        })
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, bodyResponse: String?) {
        val moviesList = moviesList(bodyResponse)
        adapter = SimpleItemRecyclerViewAdapterMovieResult(moviesList)
        // Tengo que obtener el thread main para modificar la UI
        val handler = Handler(Looper.getMainLooper())
        handler.post { recyclerView.adapter = adapter }
    }

    private fun moviesList(bodyResponse: String?): List<MovieFound> {
        val readTree = mapper.readTree(bodyResponse!!)
        val moviesList = readTree.get(jsonMainNodeKey)
        return moviesList.map { movie -> convertMovie(movie) }
    }

    private fun convertMovie(node: JsonNode): MovieFound {
        val movieAsText = node.toString()
        return mapper.readValue(movieAsText)
    }
}