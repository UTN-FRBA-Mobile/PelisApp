package com.pelisapp.core

class UserGroupApi {

    fun getGroupsByUser(user: User): List<UserGroup> {
        return getAllGroups().filter { group -> group.participants.contains(user) }
    }

    private fun getAllGroups(): List<UserGroup> {
        val martinMockUser = User(1, "Martin")
        val lucasMockUser = User(2, "Lucas")
        val javierMockUser = User(3, "Javier")
        val nicolasMockUser = User(4, "Nicolas")
        val martinianoMockUser = User(5, "Martiniano")

        val marvelFansGroup = UserGroup(1, "Marvel Fans", listOf(martinMockUser, lucasMockUser, javierMockUser))
        val tarantinoFansGroup = UserGroup(2, "Tarantino Fans", listOf(lucasMockUser, nicolasMockUser, martinianoMockUser) )
        val jamesCameronFansGroup = UserGroup(3, "James Cameron Fans", listOf(martinMockUser, martinianoMockUser))

        return listOf(marvelFansGroup, tarantinoFansGroup, jamesCameronFansGroup)
    }
}