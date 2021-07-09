package com.pelisapp.core

import android.os.StrictMode
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class MovieApi {

    private val client = OkHttpClient()
    private val mapper = jacksonObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)


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

    fun getAllMoviesFromFirebase(listener: MoviesListener){
        val database = Firebase.database
        val myRef = database.getReference("movies")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var movies = dataSnapshot.getValue<List<Movie>>()
                listener.onMoviesReceived(movies)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value from Firebase Realtime Database")
            }
        })
    }

    private fun allowNetworkPermission() {
        val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }
}