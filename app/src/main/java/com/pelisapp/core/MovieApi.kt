package com.pelisapp.core

import android.os.StrictMode
import android.util.Log
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import com.google.firebase.ktx.Firebase
import java.lang.RuntimeException
import java.util.*


class MovieApi {

    private val client = OkHttpClient()
    private val mapper = jacksonObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)


    //private val baseUrl = "https://run.mocky.io/v3/d4ef7061-43ee-4d30-a690-219249a3b50f"
    private val baseUrl = "https://www.omdbapi.com"
    private val apikey = "aeb09745" //FIXME NO COMMITEAR

    fun getMovies(): List<Movie> {
        allowNetworkPermission()

        val request = Request.Builder()
            .url("https://run.mocky.io/v3/d4ef7061-43ee-4d30-a690-219249a3b50f")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            val json = response.body!!.string()
            println("json response: $json")
            return mapper.readValue(json)
        }
    }

    fun getMoviesWithTitleMatch(title: String): List<Movie> { //TODO refactor para no repetir
        allowNetworkPermission()

        val request = Request.Builder()
            .url(urlForQuery() + titleQueryParam(title))
            .build()

        var result = client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            val json = response.body!!.string()
            println("json response: $json")
            if (json.contains("Movie not found", true) || json.contains("\"Response\": \"False\"", true))
                return emptyList()
            return listOf(mapper.readValue(json, Movie::class.java))
        }
    }

    fun getMovieByIMDBId(imdbId: String): Movie {
        allowNetworkPermission()

        val request = Request.Builder()
                .url(urlForQuery() + imdbIdQueryParam(imdbId))
                .build()

        var result = client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            val json = response.body!!.string()
            println("json response: $json")
            if (json.contains("Incorrect IMDb ID.", true) || json.contains("\"Response\": \"False\"", true))
                throw RuntimeException("No existe la pelicula con el id $imdbId")
            return mapper.readValue(json, Movie::class.java)
        }
    }

    fun getAllMoviesFromFirebase(listener: MoviesListener){
        val database = Firebase.database
        val myRef = database.getReference("movies-users")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var movies = dataSnapshot.getValue<HashMap<String, UserMovie>>()!!.values.toList()
                listener.onMoviesReceived(movies)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value from Firebase Realtime Database")
            }
        })
    }

    fun getAllMoviesFromFirebaseDB(listener: FirebaseMoviesListener){
        val database = Firebase.database
        val myRef = database.getReference("movies")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var movies = dataSnapshot.getValue<List<FirebaseMovie>>()
                listener.onFirebaseMoviesReceived(movies)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value from Firebase Realtime Database")
            }
        })
    }

    private fun urlForQuery(): String {
        return baseUrl + "/?apikey=$apikey"
    }

    private fun titleQueryParam(title: String): String {
        return "&t=$title"
    }

    private fun imdbIdQueryParam(imdbId: String): String {
        return "&i=$imdbId"
    }

    private fun allowNetworkPermission() {
        val policy = StrictMode.ThreadPolicy.Builder()
            .permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    fun updateMovieOnFirebase(userMovie: UserMovie) {
        val database = Firebase.database
        val myRef = database.getReference("movies-users")

        myRef.child("${userMovie.imdbID}").child("favoriteada").setValue(userMovie.favoriteada)
        myRef.child("${userMovie.imdbID}").child("vista").setValue(userMovie.vista)
        myRef.child("${userMovie.imdbID}").child("imdbID").setValue(userMovie.imdbID)
        Log.i("MovieApi", "Update en la base")
    }
}

class MovieSearchResult(@JsonProperty("Search") val search: List<Movie> ) {}
