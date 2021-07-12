package com.pelisapp.ui.results

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.core.*
import com.pelisapp.databinding.FragmentMoviesResultsBinding
import com.pelisapp.ui.dashboard.FirebaseMoviesAdapter
import com.pelisapp.ui.dashboard.MoviesAdapter

class MoviesResultsFragment : Fragment() {
    private var _binding: FragmentMoviesResultsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMoviesResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val viewManager = LinearLayoutManager(this.context)

        MovieApi().getAllMoviesFromFirebaseDB(object: FirebaseMoviesListener {
            override fun onFirebaseMoviesReceived(movies: List<FirebaseMovie>?) {
                PreferencesApi().getAllPreferencesFromFirebase(object: PreferencesListener {
                    override fun onPreferencesReceived(preferences: HashMap<String, Preference>?) {

                        var directors = preferences!!.values.toList().map { pref -> pref.director }
                        var genres = preferences!!.values.toList().map { pref -> pref.genre }

                        var chosenDirector = getMostRepeatedWord(directors)
                        var chosenGenre = getMostRepeatedWord(genres)

                        var chosenMovies = movies!!.filter { movie -> movie.director.equals(chosenDirector) && movie.genre.equals(chosenGenre) }

                        val viewAdapter = FirebaseMoviesAdapter(chosenMovies)

                        recyclerView = binding.resultsRecyclerView
                        recyclerView.apply{
                            layoutManager = viewManager
                            adapter = viewAdapter
                        }
                    }
                })
           }
        })
    }

    fun getMostRepeatedWord(words: List<String?>): String? {
        return words.groupingBy{ it }.eachCount().entries.maxByOrNull{ (k, v) -> v }!!.key
    }
}