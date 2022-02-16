package com.mobdeve.s12.anigan.lino.mobdevemp.dao

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s12.anigan.lino.mobdevemp.OtherItemActivity
import com.mobdeve.s12.anigan.lino.mobdevemp.ProfileActivity
import com.mobdeve.s12.anigan.lino.mobdevemp.R

class SelectTradeOfferAdapter (private val context: Context, private val yourItemList: ArrayList<YourItems>): RecyclerView.Adapter<SelectTradeOfferAdapter.myViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_pick_trade, parent, false)
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        val currentItem = yourItemList[position]
        holder.itemName.text = currentItem.itemName
        holder.itemPrice.text = currentItem.itemPrice
        holder.itemDescription.text = currentItem.itemDescription

        holder.offerButton.setOnClickListener {
            val gotoProfileActivity = Intent(context, ProfileActivity ::class.java)
            gotoProfileActivity .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            Toast.makeText(this.context,"Offer Sent", Toast.LENGTH_SHORT)
            val bundle = Bundle()
            bundle.putString("username", currentItem.itemOwner)
            gotoProfileActivity .putExtras(bundle)


            context.startActivity(gotoProfileActivity)
        }


    }

    override fun getItemCount(): Int {
        return yourItemList.size
    }

    class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val offerButton : Button = itemView.findViewById(R.id.button_offer)
        val itemName : TextView = itemView.findViewById(R.id.text_itemname)
        val itemPrice : TextView = itemView.findViewById(R.id.text_itemprice)
        val itemDescription : TextView = itemView.findViewById(R.id.text_itemdescription)

    }
}