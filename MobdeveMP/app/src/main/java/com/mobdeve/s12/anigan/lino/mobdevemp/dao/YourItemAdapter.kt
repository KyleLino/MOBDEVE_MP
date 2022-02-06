package com.mobdeve.s12.anigan.lino.mobdevemp.dao

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s12.anigan.lino.mobdevemp.R

class YourItemAdapter(private val yourItemList: ArrayList<YourItems>): RecyclerView.Adapter<YourItemAdapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_your_collection, parent, false)
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        val currentItem = yourItemList[position]
        holder.itemName.text = currentItem.itemName
    }

    override fun getItemCount(): Int {
        return yourItemList.size
    }

    class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val itemName : TextView = itemView.findViewById(R.id.text_itemname)

    }

}