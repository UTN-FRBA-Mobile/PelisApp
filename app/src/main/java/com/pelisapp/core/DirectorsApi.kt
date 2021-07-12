package com.pelisapp.core

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class DirectorsApi {

    private val database = Firebase.database
    private val myRef = database.getReference("directors")

    fun getAllDirectorsFromFirebase(listener: DirectorsListener){
        myRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var directors = dataSnapshot.getValue<List<Director>>()
                listener.onDirectorsReceived(directors)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value from Firebase Realtime Database")
            }
        })
    }
}