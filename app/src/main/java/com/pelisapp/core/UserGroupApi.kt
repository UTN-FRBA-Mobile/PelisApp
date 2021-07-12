package com.pelisapp.core

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class UserGroupApi {

    private val database = Firebase.database
    private val myRef = database.getReference("userGroups")

    fun getAllGroupsFromFirebase(listener: UserGroupsListener){

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var groups = dataSnapshot.getValue<HashMap<String, UserGroup>>()
                listener.onUserGroupsReceived(groups)
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value from Firebase Realtime Database")
            }
        })
    }

    fun saveUserGroup(userGroup: UserGroup) {
        myRef.child(userGroup.name!!).setValue(userGroup) //TODO esto ya ta andando, pero lo dejo comentado para no llenar la base con basura
        println("Voy a guardar: ${userGroup.name} y con usuarios: ${userGroup.users}")
    }

}