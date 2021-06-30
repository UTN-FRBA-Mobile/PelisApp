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

        binding.root.getViewById(R.id.misPelisButton).setOnClickListener(this)
        binding.root.getViewById(R.id.amigosButton).setOnClickListener(this)
        binding.root.getViewById(R.id.gruposButton).setOnClickListener(this)
        binding.root.getViewById(R.id.nuevoGrupoButton).setOnClickListener(this)

        return binding.root
    }

    override fun onClick(view: View?) {
        var mainActivity = activity as MainActivity
        when(view?.id){
            R.id.misPelisButton->{
                mainActivity.setMoviesView()
            }
            R.id.amigosButton->{
                //TODO: flujo amigos
            }
            R.id.gruposButton->{
                mainActivity.setGroupsView()
            }
            R.id.nuevoGrupoButton->{
                //TODO: flujo nuevo grupo
            }
        }
    }

}