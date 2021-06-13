package com.pelisapp.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.core.MovieApi
import com.pelisapp.databinding.ActivityHomeBinding

class HomeFragment : Fragment() {
    private var _binding: ActivityHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ActivityHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movies = MovieApi().getMovies()

        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = MoviesAdapter(movies)

//        recyclerView = binding.myRecyclerView.apply {
//           layoutManager = viewManager
//            adapter = viewAdapter
//        }
    }
}