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

        val marvelImageLink = "https://www.treehugger.com/thmb/m7asEl8wPZJaZzmv6oCEjS3dOXc=/300x300/smart/filters:no_upscale()/__opt__aboutcom__coeus__resources__content_migration__mnn__images__2013__02__spiderman-7fb58f51ca0b4874a136e3951d367a9a.jpg"
        val tarantinoImageLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Quentin_Tarantino_by_Gage_Skidmore.jpg/220px-Quentin_Tarantino_by_Gage_Skidmore.jpg"
        val jamesCameronImageLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/JamesCameronHWOFOct2012.jpg/300px-JamesCameronHWOFOct2012.jpg"

        val marvelFansGroup = UserGroup(1, "Marvel Fans", marvelImageLink, listOf(martinMockUser, lucasMockUser, javierMockUser))
        val tarantinoFansGroup = UserGroup(2, "Tarantino Fans", tarantinoImageLink, listOf(martinMockUser, lucasMockUser, nicolasMockUser, martinianoMockUser) )
        val jamesCameronFansGroup = UserGroup(3, "James Cameron Fans", jamesCameronImageLink, listOf(martinMockUser, martinianoMockUser))

        return listOf(marvelFansGroup, tarantinoFansGroup, jamesCameronFansGroup)
    }
}