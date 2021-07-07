package com.pelisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pelisapp.core.*
import com.pelisapp.ui.results.MoviesResultsFragment
import com.pelisapp.ui.results.WaitingForParticipantsFragment

class MoviesResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movies_results)

        UserGroupApi().getAllGroupsFromFirebase(object: UserGroupsListener{
            override fun onUserGroupsReceived(userGroups: List<UserGroup>?) {
                val userGroupName = LoggedUserRepository.getGroupName()

                val userGroup = userGroups!!.find { group -> group.name == userGroupName }

                val userNames = userGroup!!.users!!.map { user -> user.name }

                PreferencesApi().getAllPreferencesFromFirebase(object: PreferencesListener {
                    override fun onPreferencesReceived(preferences: HashMap<String, Preference>?) {
                        var users = preferences!!.keys.toList()
                        if(!containSameElements(users, userNames)){
                            var waitingForParticipantsFragment = WaitingForParticipantsFragment()
                            supportFragmentManager.beginTransaction().replace(R.id.results_container, waitingForParticipantsFragment).commitNow()
                        }else{
                            var resultsFragment = MoviesResultsFragment()
                            supportFragmentManager.beginTransaction().replace(R.id.results_container, resultsFragment).commitNow()
                        }
                    }
                })
            }
        })
    }

    fun containSameElements(list1: List<String?>, list2: List<String?>): Boolean{
        println(list1)
        println(list2)
        return list1.containsAll(list2) && list2.containsAll(list1)
    }
}