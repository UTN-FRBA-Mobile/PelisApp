package com.pelisapp.core

import com.fasterxml.jackson.annotation.JsonProperty

data class User(val id: Int, val name: String) {

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