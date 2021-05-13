package com.pelisapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.pelisapp.databinding.ActivityMainBinding
import com.pelisapp.ui.dashboard.DashboardFragment
import com.pelisapp.ui.login.LoginFragment

class MainActivity : AppCompatActivity(), LoginFragment.OnFragmentInteractionListener {
    private lateinit var loginFragment: Fragment
    private lateinit var dashboardFragment: Fragment

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        dashboardFragment = DashboardFragment()

        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager.beginTransaction().remove(loginFragment).add(R.id.container, dashboardFragment).commitNow()
    }

    override fun onSignUp() {
        TODO("Not yet implemented")
    }

}