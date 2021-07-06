package com.pelisapp.core

interface UsersListener {
    fun onUsersReceived(users: List<User>?)
}