package com.pelisapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.core.User
import com.pelisapp.core.UserGroupApi
import com.pelisapp.ui.SimpleUserGroupItemRecyclerViewAdapter
import com.pelisapp.ui.dashboard.MoviesAdapter

class UserGroupsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_groups)


        setupRecyclerView(findViewById<View>(R.id.usergroup_items_list) as RecyclerView)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val mockUser = User(1, "Martin", "https://avatarfiles.alphacoders.com/124/thumb-124091.png")
        val userGroups = UserGroupApi().getGroupsByUser(mockUser)
        recyclerView.adapter = SimpleUserGroupItemRecyclerViewAdapter(userGroups)
    }

}