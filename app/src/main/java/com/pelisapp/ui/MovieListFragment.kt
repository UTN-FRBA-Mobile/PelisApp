package com.pelisapp.ui

import MoviesAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.core.Movie
import com.pelisapp.databinding.MovieListFragmentBinding

class MovieListFragment : Fragment() {
    private lateinit var _binding: MovieListFragmentBinding
    lateinit var moviesRecyclerView : RecyclerView
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = MovieListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val movies = MovieApi().getMovies()
        val movies = getPelis()

        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = MoviesAdapter(movies)

        moviesRecyclerView = binding.rvPelis.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onMovieSelected(movie: Movie)
        fun onMovieWatched()
        fun onMovieMarkedAsFavourite()
    }

    fun getPelis(): MutableList<Movie>{
        var movies:MutableList<Movie> = ArrayList()
        movies.add(Movie("Spiderman", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg", "4.5", "ESP/ENG"))
        movies.add(Movie("Daredevil", "https://cursokotlin.com/wp-content/uploads/2017/07/daredevil.jpg", "5.0", "ESP/ENG/JPN"))
        return movies
    }
}
