package com.pelisapp.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pelisapp.MainActivity
import com.pelisapp.R
import com.pelisapp.databinding.ActivityHomeBinding

class HomeFragment : Fragment(), View.OnClickListener  {
    private var _binding: ActivityHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        println("onCreateView")
        _binding = ActivityHomeBinding.inflate(inflater, container, false)

        binding.myMoviesButton.setOnClickListener(this)
        binding.myFriendsButton.setOnClickListener(this)
        binding.groupsButton.setOnClickListener(this)
        binding.newGroupButton.setOnClickListener(this)
        binding.moviesFinderSearchView.setOnClickListener(this)
        binding.micButton.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(view: View?) {
        val mainActivity = activity as MainActivity
        when(view?.id){
            R.id.my_movies_button -> mainActivity.setMoviesView()
            R.id.my_friends_button -> mainActivity.setFriendsView()
            R.id.groups_button -> mainActivity.setGroupsView()
            R.id.new_group_button -> mainActivity.setNewGroup()
            R.id.movies_finder_search_view -> {
                val movieToFind = binding.moviesFinderSearchView.query.toString()
                mainActivity.setListOfMoviesView(movieToFind)
            }
            R.id.micButton-> mainActivity.askSpeechInput()
        }
    }

}