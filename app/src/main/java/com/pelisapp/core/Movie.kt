package com.pelisapp.core

import com.fasterxml.jackson.annotation.JsonProperty


data class Movie(val title: String, val poster: String, @JsonProperty("imdbRating") val rating: String, val language: String)