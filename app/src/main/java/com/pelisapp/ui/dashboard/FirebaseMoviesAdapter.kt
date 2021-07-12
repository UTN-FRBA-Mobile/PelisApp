package com.pelisapp.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.core.FirebaseMovie
import com.pelisapp.databinding.ViewListitemMovieBinding
import com.squareup.picasso.Picasso

class FirebaseMoviesAdapter(private val movies: List<FirebaseMovie>?) :
        RecyclerView.Adapter<FirebaseMoviesAdapter.MyViewHolder>() {

    private var _binding: ViewListitemMovieBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    class MyViewHolder(binding: ViewListitemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {

        _binding = ViewListitemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movies!![position]
        inflateView(movie)
    }

    private fun inflateView(movie: FirebaseMovie) {
        binding.apply {
            movieTitle.text = movie.title
            movieRating.text = movie.imdbRating
            movieLanguage.text = movie.language
            Picasso.get()
                    .load(movie.poster)
                    .into(moviePoster)
        }

    }

    override fun getItemCount() = movies!!.size
}