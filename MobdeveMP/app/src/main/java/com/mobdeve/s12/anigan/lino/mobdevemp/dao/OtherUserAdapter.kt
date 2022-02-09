package com.mobdeve.s12.anigan.lino.mobdevemp.dao

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s12.anigan.lino.mobdevemp.R

class OtherUserAdapter(private val otherUserList: ArrayList<OtherUsers>): RecyclerView.Adapter<OtherUserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val userView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_other_collection, parent, false)
        return UserViewHolder(userView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val currentItem = otherUserList[position]
        holder.username.text = currentItem.otherUser
    }

    override fun getItemCount(): Int {
        return otherUserList.size
    }

    class UserViewHolder(userView: View): RecyclerView.ViewHolder(userView){

        var username : TextView = userView.findViewById(R.id.text_user_name)

    }

}