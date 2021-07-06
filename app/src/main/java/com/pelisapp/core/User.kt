package com.pelisapp.core

class User(val name: String? = null, val avatarUrl: String? = null) {
    override fun equals(other: Any?): Boolean {
        other as User
        return this.name.equals(other.name)
    }
}