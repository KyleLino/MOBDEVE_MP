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
import com.mobdeve.s12.anigan.lino.mobdevemp.ProfileActivity
import com.mobdeve.s12.anigan.lino.mobdevemp.R
import com.mobdeve.s12.anigan.lino.mobdevemp.ViewCollectionActivity
import com.mobdeve.s12.anigan.lino.mobdevemp.ViewItemActivity

class YourWishlistAdapter(private val context: Context, private val yourWishlistList: ArrayList<YourWishlist>): RecyclerView.Adapter<YourWishlistAdapter.WishlistViewHolder>(){

    lateinit var databaseReference: DatabaseReference
    lateinit var database: FirebaseDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {

        val wishlistView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_your_wishlist, parent, false)
        return WishlistViewHolder(wishlistView)
    }

    override fun onBindViewHolder(holder: YourWishlistAdapter.WishlistViewHolder, position: Int) {

        //var bundle = intent.extras
        //var bundleusername = bundle!!.getString("username")

        val currentItem = yourWishlistList[position]
        val itemname = currentItem.itemName
        val itemowner = currentItem.itemOwner
        holder.wishlistName.text = currentItem.itemName

        holder.deleteButton.setOnClickListener {
            removeItem(position)
            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("UserWishListItem")

            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    //var isReal = false
                    var id = ""
                    var list = ArrayList<UserWishList>()
                    for (postsnapshot in dataSnapshot.children){

                        var value = postsnapshot.getValue<UserWishList>()

                        if (value!!.itemName == itemname && value!!.itemOwner == itemowner){
                            id = postsnapshot.key.toString()
                            databaseReference.child(id).removeValue()
                            
                            val gotoProfileActivity = Intent(context, ProfileActivity::class.java)
                            gotoProfileActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            val bundle = Bundle()
                            bundle.putString("username", itemowner)
                            gotoProfileActivity.putExtras(bundle)

                            context.startActivity(gotoProfileActivity)
                        }

                        list.add(value!!)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                }
            })
        }



    }

    override fun getItemCount(): Int {
        return yourWishlistList.size
    }

    class WishlistViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val wishlistName : TextView = itemView.findViewById(R.id.text_itemwish)
        val deleteButton : Button = itemView.findViewById(R.id.button_delete)
    }

    fun removeItem(position: Int) {
        yourWishlistList.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }
}

