package com.pelisapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.R
import com.pelisapp.UserGroupDetailActivity
import com.pelisapp.core.User
import com.pelisapp.core.UserGroup
import com.pelisapp.databinding.ViewListitemMovieBinding
import com.squareup.picasso.Picasso

class SimpleUserItemRecyclerViewAdapter(private val values: List<User>?) :
    RecyclerView.Adapter<SimpleUserItemRecyclerViewAdapter.ViewHolder>() {

    private var _binding: ViewListitemMovieBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as User
            val intent = Intent(v.context, UserGroupDetailActivity::class.java)
            v.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values!![position]
        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
        holder.bind(item)
    }

    override fun getItemCount() = values!!.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName = view.findViewById(R.id.userName) as TextView
        val userAvatar = view.findViewById(R.id.userAvatar) as ImageView

        fun bind(user: User){
            userName.text = user.name
            userAvatar.loadUrl(user.avatarUrl)
        }

        private fun ImageView.loadUrl(url: String?) {
            Picasso.get().load(url).into(this)
        }
    }
}