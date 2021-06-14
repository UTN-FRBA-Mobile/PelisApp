package com.pelisapp.core

class UserGroup(val id: Int, val name: String, val participants: List<User>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserGroup

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}