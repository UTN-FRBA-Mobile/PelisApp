package com.pelisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pelisapp.core.*
import com.pelisapp.ui.results.MoviesResultsFragment
import com.pelisapp.ui.results.WaitingForParticipantsFragment

class MoviesResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movies_results)

        PreferencesApi().getAllPreferencesFromFirebase(object: PreferencesListener {
            override fun onPreferencesReceived(preferences: HashMap<String, Preference>?) {

                var mockUsers = listOf("mbaridon", "msalazar")
                var users = preferences!!.keys.toList()
                if(users != mockUsers){
                    var waitingForParticipantsFragment = WaitingForParticipantsFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.results_container, waitingForParticipantsFragment).commitNow()
                }else{
                    var resultsFragment = MoviesResultsFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.results_container, resultsFragment).commitNow()
                }
            }
        })
    }
}