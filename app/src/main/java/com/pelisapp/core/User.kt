package com.pelisapp.core

import com.fasterxml.jackson.annotation.JsonProperty

class User(id: Int, val name: String, val avatarUrl: String) : Entity(id) {
}