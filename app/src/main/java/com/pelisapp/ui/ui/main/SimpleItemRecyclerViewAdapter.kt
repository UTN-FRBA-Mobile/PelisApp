package com.android.example.pelisApp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.pelisapp.R
import com.pelisapp.core.Movie
import com.pelisapp.ui.elements.BotonFavorita
import com.pelisapp.ui.elements.CheckboxVista
import com.squareup.picasso.Picasso
import java.io.Serializable

class SimpleItemRecyclerViewAdapter(private val originalItems: List<Movie>) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    val mapper = ObjectMapper().registerModule(KotlinModule())
    private val onClickListener: View.OnClickListener
    private var mutableItems : MutableList<Movie> = originalItems.toMutableList()

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Movie
            val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                putExtra(ItemDetailFragment.ARG_MOVIE, mapper.writeValueAsString(item))
            }
            v.context.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mutableItems[position]
        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }

        holder.bind(item)
    }

    fun filter(search: String) {
        if(search.isEmpty()) {
            mutableItems.clear()
            mutableItems.addAll(originalItems)
        } else {
            var filtered : List<Movie> = originalItems.filter { item -> item.title.orEmpty().toLowerCase().contains(search.toLowerCase()) }
            mutableItems.clear()
            mutableItems.addAll(filtered)
        }

        notifyDataSetChanged()
    }

    fun updateDataset(movies: List<Movie>) {
        this.mutableItems = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = mutableItems.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById(R.id.tvTitle) as TextView
        val rating = view.findViewById(R.id.tvIMDBRate) as TextView
        val year = view.findViewById(R.id.tvYear) as TextView
        val runtime = view.findViewById(R.id.tvRuntime) as TextView
        val rated = view.findViewById(R.id.tvRated) as TextView
        val poster = view.findViewById(R.id.ivPoster) as ImageView

        val favoriteada = BotonFavorita(view.findViewById(R.id.ivIsFavourite) as ImageView, R.drawable.icons8_star_48_on, R.drawable.icons8_star_48_off)
        val vista = CheckboxVista(view.findViewById(R.id.ivWatched) as ImageView, R.drawable.ic_checked_checkbox_48, R.drawable.ic_unchecked_checkbox_48)

        fun bind(pelicula: Movie){
            title.text = pelicula.title
            rating.text = pelicula.imdbRating
            year.text = pelicula.year
            runtime.text = pelicula.runtime
            rated.text = pelicula.rated
            favoriteada.mostrarPara(pelicula)
            vista.mostrarPara(pelicula)
            poster.loadUrl(pelicula.poster.orEmpty())
        }

        private fun ImageView.loadUrl(url: String) {
            Picasso.get().load(url).into(this)
        }
    }
}