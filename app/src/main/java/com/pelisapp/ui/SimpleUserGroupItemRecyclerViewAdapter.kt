package com.pelisapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pelisapp.R
import com.pelisapp.core.UserGroup
import com.pelisapp.databinding.ViewListitemMovieBinding
import com.squareup.picasso.Picasso

class SimpleUserGroupItemRecyclerViewAdapter(private val values: List<UserGroup>) :
        RecyclerView.Adapter<SimpleUserGroupItemRecyclerViewAdapter.ViewHolder>() {

    private var _binding: ViewListitemMovieBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.usergroup_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val groupNameTitle = view.findViewById(R.id.groupName) as TextView
        val groupParticipantsNames = view.findViewById(R.id.groupParticipantsNames) as TextView
        val groupImage = view.findViewById(R.id.groupImage) as ImageView

        fun bind(userGroup: UserGroup){
            groupNameTitle.text = userGroup.name
            groupParticipantsNames.text = userGroup.participants.map { participant -> participant.name }.joinToString { c -> c }
            groupImage.loadUrl(userGroup.imageUrl)
        }

        private fun ImageView.loadUrl(url: String) {
            Picasso.get().load(url).into(this)
        }
    }
}