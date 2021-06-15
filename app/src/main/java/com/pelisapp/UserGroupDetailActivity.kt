package com.pelisapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.core.User
import com.pelisapp.core.UserGroup
import com.pelisapp.core.UserGroupApi
import com.pelisapp.ui.SimpleUserGroupItemRecyclerViewAdapter
import com.pelisapp.ui.SimpleUserItemRecyclerViewAdapter

class UserGroupDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_details)

        val groupId = intent.getIntExtra("groupId", -1)
        val group = UserGroupApi().getGroupById(groupId)
        val groupNameTV = findViewById<TextView>(R.id.groupName)
        groupNameTV.text = group.name

        setupRecyclerView(findViewById<View>(R.id.user_items_list) as RecyclerView, group)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, group: UserGroup) {
        recyclerView.adapter = SimpleUserItemRecyclerViewAdapter(group.participants)
    }
}