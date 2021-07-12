package com.pelisapp.core

interface FirebaseMessagingListener {
    fun onDeviceTokenReceived(deciveToken: String?)
}