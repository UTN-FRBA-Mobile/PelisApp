package com.pelisapp.core

import java.util.ArrayList
import com.fasterxml.jackson.annotation.JsonProperty

object DummyContent {

    val ITEMS: MutableList<Movie> = ArrayList()
    val ITEM_MAP: MutableMap<String, Movie> = HashMap()

    init {
        addItem(spiderman())
        addItem(daredevil())
    }

    private fun daredevil() =
        Movie(
            "1",
            "Daredevil",
            "https://cursokotlin.com/wp-content/uploads/2017/07/daredevil.jpg",
            "5.0",
            "ESP/ENG/JPN",
            "Ciego desde que era joven, Matt Murdock lucha contra la injusticia de día como abogado y por la noche como Daredevil en Nueva York."
        )

    private fun spiderman() =
        Movie(
            "2",
            "Spiderman",
            "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg",
            "4.5",
            "ESP/ENG",
            "Spider-Man es un superhéroe ficticio creado por los escritores y editores Stan Lee y Steve Ditko. Apareció por primera vez en el cómic de antología Amazing Fantasy # 15, en la Edad de Plata de los cómics."
        )

    private fun addItem(item: Movie) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    data class Movie(val id: String, val title: String, val poster: String, @JsonProperty("imdbRating") val rating: String, val language: String, val details: String) {
        override fun toString(): String = title + "\n" + details
    }
}