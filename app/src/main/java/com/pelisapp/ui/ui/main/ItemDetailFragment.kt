package com.android.example.pelisApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.pelisapp.R
import com.pelisapp.core.DummyContent
import com.pelisapp.core.Movie
import com.pelisapp.ui.elements.BotonFavorita
import com.pelisapp.ui.elements.CheckboxVista

class ItemDetailFragment : Fragment() {

    val mapper = ObjectMapper().registerModule(KotlinModule())
    private var item: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_MOVIE)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = mapper.readValue(it.getString(ARG_MOVIE), Movie::class.java)
                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = item?.title
            }
        }

        val fabFavoriteada = BotonFavorita(activity!!.findViewById(R.id.fab_favoriteada), R.drawable.icons8_star_48_on, R.drawable.icons8_star_48_off) // FIXME usar binding
        fabFavoriteada.mostrarPara(item!!) // FIXME ojo con el !!

        val fabVista = CheckboxVista(activity!!.findViewById(R.id.fab_vista), R.drawable.ic_checked_checkbox_48, R.drawable.ic_unchecked_checkbox_48) // FIXME usar binding
        fabVista.mostrarPara(item!!) // FIXME ojo con el !!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // Show the dummy content as text in a TextView.
        item?.let {
            rootView.findViewById<TextView>(R.id.item_detail).text = it.plot
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_MOVIE = "movie"
    }
}