package com.pelisapp.core

interface PreferencesListener {
    fun onPreferencesReceived(preferences: HashMap<String, Preference>?)
}