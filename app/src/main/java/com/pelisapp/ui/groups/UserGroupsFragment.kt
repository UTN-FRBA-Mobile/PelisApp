package com.pelisapp.ui.groups

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.R
import com.pelisapp.core.*
import com.pelisapp.databinding.FragmentUserGroupsBinding
import com.pelisapp.ui.SimpleUserGroupItemRecyclerViewAdapter

class UserGroupsFragment : Fragment() {
    private var _binding: FragmentUserGroupsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUserGroupsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewManager = LinearLayoutManager(this.context)

        val loggedUser = LoggedUserRepository.getUser()

        UserGroupApi().getAllGroupsFromFirebase(object: UserGroupsListener {
            override fun onUserGroupsReceived(userGroups: List<UserGroup>?) {

                var groupsByUser = userGroups!!.filter { group -> group.users!!.contains(loggedUser) }

                val viewAdapter = SimpleUserGroupItemRecyclerViewAdapter(groupsByUser)

                recyclerView = binding.userGroupListFrameLayout.findViewById(R.id.usergroup_items_list)
                recyclerView.apply{
                    layoutManager = viewManager
                    adapter = viewAdapter
                }
            }
        })
    }
}