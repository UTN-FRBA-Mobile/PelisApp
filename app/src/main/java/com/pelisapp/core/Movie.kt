package com.pelisapp.core

import com.fasterxml.jackson.annotation.JsonProperty


data class Movie(val title: String? = null, val poster: String? = null, @JsonProperty("imdbRating") val rating: String? = null, val language: String? = null, val director: String? = null, val genre: String? = null)