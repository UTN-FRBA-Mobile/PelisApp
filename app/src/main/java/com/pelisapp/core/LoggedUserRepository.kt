package com.pelisapp.core

object LoggedUserRepository {
    private var userName: String = ""
    private var groupName: String = "James Cameron Fans"

    fun setUserName(userName: String){
        this.userName = userName
    }

    fun getUser(): User{
        return User(userName)
    }

    fun setGroupName(groupName: String){
        this.groupName = groupName
    }

    fun getGroupName(): String{
        return groupName
    }
}