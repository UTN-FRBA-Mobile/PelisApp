package com.pelisapp.core

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class PreferencesApi {
    private val database = Firebase.database
    private val preferencesRef = database.getReference("preferences")

    fun saveUserPreferenceIntoFirebase(userName: String, preference: Preference){
        val ref = preferencesRef.child(userName)
        ref.setValue(preference)
    }

    fun getAllPreferencesFromFirebase(listener: PreferencesListener){
        preferencesRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var preferences = snapshot.getValue<HashMap<String, Preference>>()
                listener.onPreferencesReceived(preferences)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value from Firebase Realtime Database")
            }
        })
    }
}