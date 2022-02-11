package com.mobdeve.s12.anigan.lino.mobdevemp.dao

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s12.anigan.lino.mobdevemp.R

class UserWishlistAdapter (private val userWishlistList: ArrayList<UserWishList>): RecyclerView.Adapter<UserWishlistAdapter.WishlistViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {

        val wishlistView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_user_wishlist, parent, false)
        return WishlistViewHolder(wishlistView)
    }

    override fun onBindViewHolder(holder: UserWishlistAdapter.WishlistViewHolder, position: Int) {

        val currentItem = userWishlistList[position]
        holder.wishlistName.text = currentItem.itemName

    }

    override fun getItemCount(): Int {
        return userWishlistList.size
    }

    class WishlistViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val wishlistName : TextView = itemView.findViewById(R.id.text_userWishlistItem)

    }

}