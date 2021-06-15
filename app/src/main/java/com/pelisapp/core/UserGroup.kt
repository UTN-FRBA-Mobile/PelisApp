package com.pelisapp.core

class UserGroup(id: Int, val name: String, val imageUrl: String, val participants: List<User>) : Entity(id) {
}