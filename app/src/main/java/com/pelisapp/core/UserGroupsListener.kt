package com.pelisapp.core

interface UserGroupsListener{
    fun onUserGroupsReceived(userGroups: HashMap<String, UserGroup>?)
}