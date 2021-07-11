package com.pelisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.pelisapp.core.*

class MoviesFilterActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_filter)

        DirectorsApi().getAllDirectorsFromFirebase(object: DirectorsListener{
            override fun onDirectorsReceived(directors: List<Director>?) {
                var directorSpinner = findViewById<Spinner>(R.id.director_spinner)

                var directorNames = directors!!.map { director -> director.name }

                var arrayAdapter = ArrayAdapter(this@MoviesFilterActivity, android.R.layout.simple_spinner_item, directorNames)

                directorSpinner.adapter = arrayAdapter
            }
        })

        val saveButton = findViewById<Button>(R.id.save_button)
        saveButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.save_button->{
                savePreferences()
                setResultsView()
            }
        }
    }

    private fun savePreferences() {
        var userName = LoggedUserRepository.getUser().name

        var genreSpinner = findViewById<Spinner>(R.id.genre_spinner)
        var genre = genreSpinner.selectedItem.toString()

        var directorSpinner = findViewById<Spinner>(R.id.director_spinner)
        var director = directorSpinner.selectedItem.toString()

        var preference = Preference(userName, genre, director)

        if (userName != null) {
            PreferencesApi().saveUserPreferenceIntoFirebase(userName, preference)
        }
    }

    private fun setResultsView() {
        val intent = Intent(this, MoviesResultsActivity::class.java)
        startActivity(intent)
    }
}