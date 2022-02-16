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
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.mobdeve.s12.anigan.lino.mobdevemp.PickTradeActivity
import com.mobdeve.s12.anigan.lino.mobdevemp.ProfileActivity
import com.mobdeve.s12.anigan.lino.mobdevemp.R

class SelectTradeOfferAdapter (private val context: Context, private val yourItemList: ArrayList<YourItems>): RecyclerView.Adapter<SelectTradeOfferAdapter.myViewHolder>(){

    lateinit var databaseReference: DatabaseReference
    lateinit var database: FirebaseDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_pick_trade, parent, false)
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("UserItem")

        val currentItem = yourItemList[position]
        holder.itemName.text = currentItem.itemName
        holder.itemPrice.text = currentItem.itemPrice
        holder.itemDescription.text = currentItem.itemDescription

        holder.offerButton.setOnClickListener {




            // must CREATE

            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    var isReal = false
                    var offeritemid = ""
                    var list = ArrayList<UserItem>()
                    for (postsnapshot in dataSnapshot.children){

                        var value = postsnapshot.getValue<UserItem>()

                        if (value!!.itemName == currentItem.itemName && value!!.itemPrice == currentItem.itemPrice && value!!.itemDescription == currentItem.itemDescription && value!!.itemOwner == currentItem.itemOwner){
                            isReal = true
                            offeritemid = postsnapshot.key.toString()
                        }

                        list.add(value!!)
                    }

                    if (isReal){
                        //databaseReference.child(id).removeValue()
                        val gotoProfileActivity = Intent(context, ProfileActivity ::class.java)
                        gotoProfileActivity .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        //Toast.makeText(this.context,"Offer Sent", Toast.LENGTH_SHORT)
                        val bundle = Bundle()
                        bundle.putString("username", currentItem.itemOwner)
                        bundle.putString("offeritemid", offeritemid)

                        //val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                        //val savedloggedin = sharedPreferences.getString("loggedinuser", null)*/
                        gotoProfileActivity .putExtras(bundle)


                        context.startActivity(gotoProfileActivity)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                }
            })


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