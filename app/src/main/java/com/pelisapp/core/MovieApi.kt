package com.pelisapp.core

import android.os.StrictMode
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
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

    private fun allowNetworkPermission() {
        val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }
}