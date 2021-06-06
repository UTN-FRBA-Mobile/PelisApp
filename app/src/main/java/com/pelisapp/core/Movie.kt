package com.pelisapp.core
import com.fasterxml.jackson.annotation.JsonProperty;


class Movie constructor (val id: String, val title: String, val poster: String, @JsonProperty("imdbRating") val rating: String, val language: String, val details: String, var favorita : Boolean, var vista : Boolean) {
    override fun toString(): String = title + "\n" + details

    fun cambiarEstadoFavoriteado() {
        this.favorita = !this.favorita
    }

    fun cambiarEstadoVista() {
        this.vista = !this.vista
    }
}