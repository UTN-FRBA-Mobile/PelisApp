package com.pelisapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.core.User
import com.pelisapp.databinding.FragmentNewGroupBinding
import com.pelisapp.ui.groups.new.UsersAdapter


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

        val users: List<User> = getUsersMock()//UsersApi().getUsers() TODO implementar
        usersAdapter = UsersAdapter(users)

        usersRecyclerView = binding.myRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = usersAdapter
        }

        usersRecyclerView.adapter = usersAdapter
    }

    fun getUsersMock(): List<User> {
        val carlosTevez = User("Carlos Tevez", "https://media.minutouno.com/p/0b1aacbd466924313cc533d9c63e6891/adjuntos/150/imagenes/038/940/0038940352/tevez.jpg")
        val messi = User("Lionel Messi", "https://images.mediotiempo.com/jLgLLQXf9QwnT17Ssp_Is1DRUU0=/958x596/uploads/media/2021/06/12/messi-no-anoto-ante-colombia.jpg")
        val dios = User("Dios", "https://e00-marca.uecdn.es/assets/multimedia/imagenes/2018/06/26/15300467456610.jpg")
        val pajaro = User("Pajaro Cannigia", "https://www.ecured.cu/images/thumb/2/26/7cdcc4a0bbe3d30db7df407c28fdc6e0.jpg/260px-7cdcc4a0bbe3d30db7df407c28fdc6e0.jpg")
        val corbatta = User("Corbatta", "https://racingmaniacos.com.ar/wp-content/uploads/2017/11/descarga.jpg")
        return listOf(carlosTevez, messi, dios, pajaro, corbatta)
    }

}