package com.mobdeve.s12.anigan.lino.mobdevemp.dao

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s12.anigan.lino.mobdevemp.R


class UserItemAdapter(private val userItemList: ArrayList<UserItem>): RecyclerView.Adapter<UserItemAdapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_other_user_items, parent, false)
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        val currentItem = userItemList[position]
        holder.itemName.text = currentItem.itemName

    }

    override fun getItemCount(): Int {
        return userItemList.size
    }

    class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val itemName : TextView = itemView.findViewById(R.id.text_otherUserItem)
        val viewButton : Button = itemView.findViewById(R.id.button_view)

    }

}