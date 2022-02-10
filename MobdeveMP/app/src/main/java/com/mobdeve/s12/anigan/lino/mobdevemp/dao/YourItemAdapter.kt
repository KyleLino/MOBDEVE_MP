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

class YourItemAdapter(private val context: Context, private val yourItemList: ArrayList<YourItems>): RecyclerView.Adapter<YourItemAdapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_your_collection, parent, false)
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        val currentItem = yourItemList[position]
        holder.itemName.text = currentItem.itemName

        holder.viewButton.setOnClickListener {
            val gotoViewItemActivity = Intent(context, ViewItemActivity::class.java)
            gotoViewItemActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            val bundle = Bundle()
            bundle.putString("itemname", holder.itemName.text as String?)
            bundle.putString("itemprice", currentItem.itemPrice)
            bundle.putString("itemdescription", currentItem.itemDescription)
            bundle.putString("itemowner", currentItem.itemOwner)
            gotoViewItemActivity.putExtras(bundle)

            context.startActivity(gotoViewItemActivity)
        }
    }

    override fun getItemCount(): Int {
        return yourItemList.size
    }

    class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


        val itemName : TextView = itemView.findViewById(R.id.text_itemname)
        val viewButton : Button = itemView.findViewById(R.id.button_view)

    }

    fun gotoView(position: Int){

    }

}