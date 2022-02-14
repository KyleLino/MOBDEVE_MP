package com.mobdeve.s12.anigan.lino.mobdevemp.dao

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s12.anigan.lino.mobdevemp.R
import com.mobdeve.s12.anigan.lino.mobdevemp.ViewItemActivity
import com.mobdeve.s12.anigan.lino.mobdevemp.ViewOtherUserProfileActivity

class OtherUserAdapter(private val context: Context, private val otherUserList: ArrayList<OtherUsers>): RecyclerView.Adapter<OtherUserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val userView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_other_collection, parent, false)
        return UserViewHolder(userView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val currentItem = otherUserList[position]
        holder.otherUser.text = currentItem.username

        holder.viewButton.setOnClickListener {
            val gotoViewOtherUserProfileActivity = Intent(context, ViewOtherUserProfileActivity::class.java)
            gotoViewOtherUserProfileActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            val bundle = Bundle()
            bundle.putString("otherusername", currentItem.username)
            gotoViewOtherUserProfileActivity.putExtras(bundle)

            context.startActivity(gotoViewOtherUserProfileActivity)
        }
    }

    override fun getItemCount(): Int {
        return otherUserList.size
    }

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var otherUser : TextView = itemView.findViewById(R.id.text_user_name)
        val viewButton : Button = itemView.findViewById(R.id.button_view_other)
    }

}