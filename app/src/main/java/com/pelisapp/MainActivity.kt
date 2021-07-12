package com.pelisapp

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pelisapp.core.LoggedUserRepository
import com.pelisapp.databinding.ActivityMainBinding
import com.pelisapp.ui.dashboard.HomeFragment
import com.pelisapp.ui.friends.FriendsFragment
import com.pelisapp.ui.groups.UserGroupsFragment
import com.pelisapp.ui.login.LoginFragment
import java.util.*

class MainActivity : AppCompatActivity(), LoginFragment.OnFragmentInteractionListener,
    UserGroupsFragment.UserGroupsInteractionListener {
    private lateinit var loginFragment: Fragment
    private lateinit var homeFragment: Fragment
    private lateinit var groupsFragment: Fragment
    private lateinit var friendsFragment: Fragment

    lateinit var binding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth

    private val RQ_SPEECH_REC = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            loginFragment = LoginFragment()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, loginFragment)
                    .commitNow()
        }
    }

    override fun onLogin(username: String, password: String) {
        val userWithEmail = username.plus("@mail.com")
        auth.signInWithEmailAndPassword(userWithEmail, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    LoggedUserRepository.setUserName(username)
                    homeFragment = HomeFragment()
                    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    supportFragmentManager.beginTransaction().remove(loginFragment).add(R.id.container, homeFragment).commitNow()
                } else {
                    val incorrectUserPasswordTextView = findViewById<TextView>(R.id.incorrect_user_password)
                    incorrectUserPasswordTextView.visibility = View.VISIBLE
                }
            }
    }

    override fun onSignUp() {
        TODO("Not yet implemented")
    }

    // TODO: Solo para fines de desarrollo
    override fun onDirectLogin() {
        this.onLogin("npatronelli", "npatronelli123")
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStackImmediate()
        else super.onBackPressed();
    }

    fun setMoviesView(){
        val intent = Intent(this, ItemListActivity::class.java)
        startActivity(intent)
    }

    fun setGroupsView(){
        groupsFragment = UserGroupsFragment()
        supportFragmentManager.beginTransaction()
            .addToBackStack("groups")
            .remove(homeFragment)
            .add(R.id.container, groupsFragment)
            .commit()
    }

    fun setFriendsView(){
        friendsFragment = FriendsFragment()
        supportFragmentManager.beginTransaction()
            .addToBackStack("friends")
            .remove(homeFragment)
            .add(R.id.container, friendsFragment)
            .commit()
    }

    fun setListOfMoviesFoundView(movieToFind: String) {
        val intent = Intent(this, ListOfMoviesFoundActivity::class.java)
        intent.putExtra("movieToFind", movieToFind)
        startActivity(intent)
    }

    fun askSpeechInput() {
        if (!SpeechRecognizer.isRecognitionAvailable(this))
            Toast.makeText(this, "Speech recognition is not available", Toast.LENGTH_SHORT).show()
        else {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something!")
            startActivityForResult(intent, RQ_SPEECH_REC)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK) {
        if (requestCode == RQ_SPEECH_REC && data != null) {
            val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val recognizedText = result?.get(0).toString()
            val searchView = findViewById<SearchView>(R.id.movies_finder_search_view)
            searchView.setQuery(recognizedText, false)
            searchView.clearFocus()
        }
//        Para fines de testings
        val searchView = findViewById<SearchView>(R.id.movies_finder_search_view)
        searchView.setQuery("pokemon", false)
        searchView.clearFocus()
    }

    override fun setNewGroup() {
        val intent = Intent(this, NewGroupActivity::class.java)
        startActivity(intent)
    }
}