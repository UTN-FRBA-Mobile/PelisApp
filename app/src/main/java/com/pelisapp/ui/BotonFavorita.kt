package com.pelisapp.ui

import android.widget.ImageView
import com.pelisapp.core.Movie

class BotonFavorita(val view: ImageView, val icono_on: Int, val icono_off: Int) {
    lateinit var pelicula : Movie

    init {
        view.setOnClickListener {
            favoritear(it as ImageView)
        }
    }

    fun favoritear(v: ImageView) {
        pelicula.cambiarEstadoFavoriteado()
        this.mostrarPara(pelicula)
    }

    fun mostrarPara(pelicula: Movie) {
        this.pelicula = pelicula
        if (this.pelicula.favorita) {
            this.view.setImageResource(this.icono_on)
        } else {
            this.view.setImageResource(this.icono_off)
        }
    }
}
