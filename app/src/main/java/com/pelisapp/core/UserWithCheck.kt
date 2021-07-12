package com.pelisapp.core

class UserWithCheck(var name: String, var avatarUrl: String, var check: Boolean) {
    override fun toString(): String {
        return "UserWithCheck(name='$name', avatarUrl='$avatarUrl', check=$check)"
    }
}
