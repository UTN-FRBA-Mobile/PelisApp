package com.pelisapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.core.*
import com.pelisapp.databinding.FragmentNewGroupBinding
import com.pelisapp.ui.users.UsersAdapter


class NewGroupActivity : AppCompatActivity() {
    private var _binding: FragmentNewGroupBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var usersAdapter: UsersAdapter
    private lateinit var usersRecyclerView: RecyclerView

    private var userList: List<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentNewGroupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.usernameSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                println("Press querysubmit")
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                println("Press querytextchange")
                usersAdapter.filter.filter(newText)
                return true
            }
        })

        UsersApi().getAllUsersFromFirebase(object: UsersListener {
            override fun onUsersReceived(users: List<User>?) {
                userList = users!!
                usersAdapter = UsersAdapter(users!!.map { user -> UserWithCheck(user.name!!, user.avatarUrl!!, false) })

                usersRecyclerView = binding.myRecyclerView.apply {
                    layoutManager = LinearLayoutManager(this.context)
                    adapter = usersAdapter
                }

                usersRecyclerView.adapter = usersAdapter
            }
        })

        binding.newGroupButton.setOnClickListener {
            val image = "https://virtualscreenings.com/wp-content/plugins/profilegrid-user-profiles-groups-and-communities/public/partials/images/default-group.png"
            val userGroup = UserGroup(getNewGroupName(), image, usersAdapter.usersCheckeds())
            UserGroupApi().saveUserGroup(userGroup)
            val text = "Grupo creado!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }
    }

    fun getNewGroupName(): String {
        return binding.newGroupName.text.toString()
    }

}