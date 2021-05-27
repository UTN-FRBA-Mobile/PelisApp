package com.pelisapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pelisapp.databinding.ActivityMainBinding
import com.pelisapp.ui.dashboard.DashboardFragment
import com.pelisapp.ui.login.LoginFragment

class MainActivity : AppCompatActivity(), LoginFragment.OnFragmentInteractionListener {
    private lateinit var loginFragment: Fragment
    private lateinit var dashboardFragment: Fragment

    lateinit var binding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            loginFragment = LoginFragment()

            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, loginFragment)
                    .commitNow()
        }
    }

    override fun onLogin(username: String, password: String) {
        var userWithEmail = username.plus("@mail.com")

        auth.signInWithEmailAndPassword(userWithEmail, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    dashboardFragment = DashboardFragment()

                    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

                    supportFragmentManager.beginTransaction().remove(loginFragment).add(R.id.container, dashboardFragment).commitNow()
                } else {
                    val incorrectUserPasswordTextView = findViewById<TextView>(R.id.incorrect_user_password)

                    incorrectUserPasswordTextView.visibility = View.VISIBLE
                }
            }
    }

    override fun onSignUp() {
        TODO("Not yet implemented")
    }

}