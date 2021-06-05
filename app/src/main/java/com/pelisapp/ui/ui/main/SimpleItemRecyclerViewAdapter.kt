package com.android.example.pelisApp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.R
import com.pelisapp.core.Movie
import com.pelisapp.ui.BotonFavorita
import com.squareup.picasso.Picasso

class SimpleItemRecyclerViewAdapter(private val values: List<Movie>) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Movie
            val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id)
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
        val item = values[position]
        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }

        holder.bind(item)
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById(R.id.tvTitle) as TextView
        val rating = view.findViewById(R.id.tvIMDBRate) as TextView
        val poster = view.findViewById(R.id.ivPoster) as ImageView
        val favoriteada = BotonFavorita(view.findViewById(R.id.ivIsFavourite) as ImageView, R.drawable.icons8_star_48_on, R.drawable.icons8_star_48_off)

        fun bind(pelicula: Movie){
            title.text = pelicula.title
            rating.text = pelicula.rating
            favoriteada.mostrarPara(pelicula)
            poster.loadUrl(pelicula.poster)
        }

        private fun ImageView.loadUrl(url: String) {
            Picasso.get().load(url).into(this)
        }
    }
}