package com.pelisapp.ui.elements

import android.widget.ImageView

class CheckboxVista(view: ImageView, icono_on: Int, icono_off: Int) :
    CheckDelUsuario(view, icono_on, icono_off){
    override fun cambiarEstadoPelicula() {
        pelicula.cambiarEstadoVista()
    }

    override fun estadoActualPelicula(): Boolean {
        return this.pelicula.vista
    }
}
