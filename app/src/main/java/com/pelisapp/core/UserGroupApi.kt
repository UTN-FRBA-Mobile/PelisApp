package com.pelisapp.core

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class UserGroupApi {

    fun getAllGroupsFromFirebase(listener: UserGroupsListener){
        val database = Firebase.database
        val myRef = database.getReference("userGroups")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var groups = dataSnapshot.getValue<List<UserGroup>>()
                listener.onUserGroupsReceived(groups)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value from Firebase Realtime Database")
            }
        })
    }
}