package com.pelisapp.core

object LoggedUserRepository {
    private var userName: String = ""

    fun setUserName(userName: String){
        this.userName = userName
    }

    fun getUser(): User{
        return User(userName)
    }
}