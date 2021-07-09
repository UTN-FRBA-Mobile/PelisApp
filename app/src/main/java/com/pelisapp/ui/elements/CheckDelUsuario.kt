package com.pelisapp.ui.elements

import android.widget.ImageView
import com.pelisapp.core.Movie

abstract class CheckDelUsuario(val view: ImageView, val icono_on: Int, val icono_off: Int) {
    lateinit var pelicula : Movie

    init {
        view.setOnClickListener {
            onClick()
        }
    }

    fun onClick() {
        this.cambiarEstadoPelicula()
        this.mostrarPara(pelicula)
    }

    fun mostrarPara(pelicula: Movie) {
        this.pelicula = pelicula
        if (estadoActualPelicula()) {
            this.view.setImageResource(this.icono_on)
        } else {
            this.view.setImageResource(this.icono_off)
        }
    }

    abstract fun cambiarEstadoPelicula()
    abstract fun estadoActualPelicula() : Boolean
}