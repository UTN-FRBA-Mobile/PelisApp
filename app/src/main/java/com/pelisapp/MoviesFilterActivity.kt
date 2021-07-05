package com.pelisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MoviesFilterActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_filter)

        val saveButton = findViewById<Button>(R.id.save_button)
        saveButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.save_button->{
                setResultsView()
            }
        }
    }

    private fun setResultsView() {
        val intent = Intent(this, MoviesResultsActivity::class.java)
        startActivity(intent)
    }
}