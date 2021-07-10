package com.pelisapp.ui.groups

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.R
import com.pelisapp.core.LoggedUserRepository
import com.pelisapp.core.UserGroup
import com.pelisapp.core.UserGroupApi
import com.pelisapp.core.UserGroupsListener
import com.pelisapp.databinding.FragmentUserGroupsBinding
import com.pelisapp.ui.SimpleUserGroupItemRecyclerViewAdapter

class UserGroupsFragment : Fragment() {
    private var _binding: FragmentUserGroupsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private var listener: UserGroupsInteractionListener? = null

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

        binding.createNewGroupBtn.setOnClickListener {
            listener!!.createNewGroup()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is UserGroupsInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement UserGroupsInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface UserGroupsInteractionListener {
        fun createNewGroup()
    }
}