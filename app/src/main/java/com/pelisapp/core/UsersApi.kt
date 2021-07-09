package com.pelisapp.core

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class UsersApi {
    private val database = Firebase.database
    private val usersRef = database.getReference("users")

    fun getAllUsersFromFirebase(listener: UsersListener){
        usersRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var users = snapshot.getValue<List<User>>()
                listener.onUsersReceived(users)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value from Firebase Realtime Database")
            }
        })
    }
}