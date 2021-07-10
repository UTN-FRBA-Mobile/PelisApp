package com.pelisapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.core.User
import com.pelisapp.core.UsersApi
import com.pelisapp.core.UsersListener
import com.pelisapp.databinding.FragmentNewGroupBinding
import com.pelisapp.ui.users.UsersAdapter


class NewGroupActivity : AppCompatActivity() {
    private var _binding: FragmentNewGroupBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var usersAdapter: UsersAdapter
    private lateinit var usersRecyclerView: RecyclerView

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
                usersAdapter = UsersAdapter(users!!)

                usersRecyclerView = binding.myRecyclerView.apply {
                    layoutManager = LinearLayoutManager(this.context)
                    adapter = usersAdapter
                }

                usersRecyclerView.adapter = usersAdapter
            }
        })
    }

}