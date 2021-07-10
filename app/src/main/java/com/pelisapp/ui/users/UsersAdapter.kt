package com.pelisapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.core.User
import com.pelisapp.databinding.ViewListitemUserBinding
import com.squareup.picasso.Picasso

class UsersAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UsersAdapter.MyViewHolder>(), Filterable {

    var userFilterList: List<User> = ArrayList()

    init {
        userFilterList = users
    }

    private var _binding: ViewListitemUserBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    class MyViewHolder(binding: ViewListitemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {

        _binding = ViewListitemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        println("position: $position")
        val user = userFilterList[position]
        inflateView(user)
    }

    private fun inflateView(user: User) {
        println("inflateView: $user")
        binding.apply {
            username.text = user.name
            Picasso.get()
                .load(user.avatarUrl)
                .into(userProfile)
        }

    }

    override fun getItemCount() = userFilterList.size

    @ExperimentalStdlibApi
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                println("charSearch: $charSearch")
                if (charSearch.isEmpty()) {
                    userFilterList = users
                } else {
                    println("filtrando usuarios... con charSearch: ${charSearch}")
                    userFilterList = users.filter { user -> user.name!!.lowercase().contains(charSearch.lowercase()) }
                    println("Usuarios filtrados: ${userFilterList}")
                }
                val filterResults = FilterResults()
                filterResults.values = userFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                println("results?.values : ${results?.values}")
                userFilterList = results?.values as List<User>
                notifyDataSetChanged()
            }

        }
    }

}