package com.pelisapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pelisapp.core.Movie
import com.pelisapp.databinding.ActivityMainBinding
import com.pelisapp.ui.MovieListFragment

class MainActivity : AppCompatActivity(), MovieListFragment.OnFragmentInteractionListener{
    private lateinit var movieListFragment: Fragment
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            movieListFragment = MovieListFragment()

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, movieListFragment)
                    .commitNow()
        }
    }

    override fun onMovieSelected(movie: Movie) {
        // TODO aca deberia disparar el otro fragment que muestra el detalle de la peli.
//        dashboardFragment = DashboardFragment()
//
//        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//
//        supportFragmentManager.beginTransaction().remove(loginFragment).add(R.id.container, dashboardFragment).commitNow()
    }

    override fun onMovieWatched() {
        TODO("Not yet implemented")
    }

    override fun onMovieMarkedAsFavourite() {
        TODO("Not yet implemented")
    }
}