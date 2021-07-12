package com.pelisapp.core

import com.fasterxml.jackson.annotation.JsonProperty

class MovieFound constructor(
        @JsonProperty("Title") val title: String,
        @JsonProperty("Year") val year: String,
        @JsonProperty("imdbID") val imdbID: String,
        @JsonProperty("Type") val type: String,
        @JsonProperty("Poster") val poster: String,
        var favorita : Boolean = false,
        var vista : Boolean = false
){
    override fun toString(): String = title + "\n" + imdbID
    fun cambiarEstadoFavoriteado() = run { this.favorita = !this.favorita }
    fun cambiarEstadoVista() = run { this.vista = !this.vista }
}
