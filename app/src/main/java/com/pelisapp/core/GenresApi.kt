package com.pelisapp.core

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class GenresApi {

    private val database = Firebase.database
    private val myRef = database.getReference("genres")

    fun getAllGenresFromFirebase(listener: GenresListener){
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var genres = dataSnapshot.getValue<List<Genre>>()
                listener.onGenresReceived(genres)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value from Firebase Realtime Database")
            }
        })
    }
}