package com.pelisapp.ui.friends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.R
import com.pelisapp.core.LoggedUserRepository
import com.pelisapp.core.User
import com.pelisapp.core.UsersApi
import com.pelisapp.core.UsersListener
import com.pelisapp.databinding.FragmentFriendsBinding
import com.pelisapp.ui.SimpleUserItemRecyclerViewAdapter

class FriendsFragment : Fragment() {
    private var _binding: FragmentFriendsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentFriendsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val viewManager = LinearLayoutManager(this.context)

        val loggedUser = LoggedUserRepository.getUser()

        UsersApi().getAllUsersFromFirebase(object: UsersListener{
            override fun onUsersReceived(users: List<User>?) {
                val usersWithoutSelf = users!!.filter { user -> user.name != loggedUser.name }

                val viewAdapter = SimpleUserItemRecyclerViewAdapter(usersWithoutSelf)

                recyclerView = binding.friendsFrameLayout.findViewById(R.id.user_items_list)
                recyclerView.apply{
                    layoutManager = viewManager
                    adapter = viewAdapter
                }
            }
        })
    }
}