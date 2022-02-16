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
import com.mobdeve.s12.anigan.lino.mobdevemp.OtherItemActivity
import com.mobdeve.s12.anigan.lino.mobdevemp.R
import com.mobdeve.s12.anigan.lino.mobdevemp.ViewItemActivity


class UserItemAdapter(private val context: Context, private val userItemList: ArrayList<UserItem>): RecyclerView.Adapter<UserItemAdapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_other_user_items, parent, false)
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        val currentItem = userItemList[position]
        holder.itemName.text = currentItem.itemName

        holder.viewButton.setOnClickListener {
            val gotoOtherItemActivity = Intent(context, OtherItemActivity ::class.java)
            gotoOtherItemActivity .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            val bundle = Bundle()
            bundle.putString("otheritemname", holder.itemName.text as String?)
            bundle.putString("otheritemprice", currentItem.itemPrice)
            bundle.putString("otheritemdescription", currentItem.itemDescription)
            bundle.putString("otheritemowner", currentItem.itemOwner)
            gotoOtherItemActivity .putExtras(bundle)

            context.startActivity(gotoOtherItemActivity)
        }
        

    }

    override fun getItemCount(): Int {
        return userItemList.size
    }

    class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val itemName : TextView = itemView.findViewById(R.id.text_otherUserItem)
        val viewButton : Button = itemView.findViewById(R.id.button_view)

    }

}
