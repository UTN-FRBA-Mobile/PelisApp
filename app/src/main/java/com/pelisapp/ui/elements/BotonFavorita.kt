package com.pelisapp.ui.elements

import android.widget.ImageView
import com.pelisapp.core.Movie

class BotonFavorita(view: ImageView, icono_on: Int, icono_off: Int) :
    CheckDelUsuario(view, icono_on, icono_off) {

    override fun cambiarEstadoPelicula() {
        pelicula.cambiarEstadoFavoriteado()
    }

    override fun estadoActualPelicula(): Boolean {
        return this.pelicula.favorita
    }
}