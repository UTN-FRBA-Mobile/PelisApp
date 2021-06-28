package com.pelisapp.core

open class Entity () {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Entity

        //if (id != other.id) return false

        return true
    }

    /*override fun hashCode(): Int {
        return id
    }*/
}