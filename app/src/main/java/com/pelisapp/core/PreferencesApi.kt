package com.pelisapp.core

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PreferencesApi {
    fun saveUserPreferenceIntoFirebase(userName: String, preference: Preference){
        val database = Firebase.database
        val myRef = database.getReference("preferences").child(userName)
        myRef.setValue(preference)
    }
}