package com.pelisapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.core.UserGroup
import com.pelisapp.core.UserGroupApi
import com.pelisapp.core.UserGroupsListener
import com.pelisapp.ui.SimpleUserItemRecyclerViewAdapter

class UserGroupDetailActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_details)

        val chooseMovieButton = findViewById<Button>(R.id.chooseAMovieBtn)
        chooseMovieButton.setOnClickListener(this)

        val groupId = intent.getStringExtra("groupId")
        UserGroupApi().getAllGroupsFromFirebase(object: UserGroupsListener {
            override fun onUserGroupsReceived(userGroups: List<UserGroup>?) {

                var group = userGroups!!.first { group -> group.name == groupId }

                val groupNameTV = findViewById<TextView>(R.id.groupName)
                groupNameTV.text = group.name

                setupRecyclerView(findViewById<View>(R.id.user_items_list) as RecyclerView, group)
            }
        });
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, group: UserGroup) {
        recyclerView.adapter = SimpleUserItemRecyclerViewAdapter(group.users)
    }

    override fun onClick(view: View?) {
        println(view)
        when(view?.id){
            R.id.chooseAMovieBtn->{
                setFiltersView()
            }
        }
    }

    private fun setFiltersView(){
        val intent = Intent(this, MoviesFilterActivity::class.java)
        startActivity(intent)
    }
}