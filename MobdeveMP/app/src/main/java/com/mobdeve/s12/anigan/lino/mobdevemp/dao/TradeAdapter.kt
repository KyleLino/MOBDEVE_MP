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

class TradeAdapter (private val context: Context, private val userItemList: ArrayList<UserItem>): RecyclerView.Adapter<TradeAdapter.myViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_trade_offers, parent, false)
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

        val itemName : TextView = itemView.findViewById(R.id.offer)

    }

    fun gotoView(position: Int){

    }




}