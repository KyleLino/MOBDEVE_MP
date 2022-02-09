package com.mobdeve.s12.anigan.lino.mobdevemp.dao

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s12.anigan.lino.mobdevemp.R

class YourWishlistAdapter (private val yourWishlistList: ArrayList<YourWishlist>): RecyclerView.Adapter<YourWishlistAdapter.WishlistViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {

        val wishlistView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_your_wishlist, parent, false)
        return WishlistViewHolder(wishlistView)
    }

    override fun onBindViewHolder(holder: YourWishlistAdapter.WishlistViewHolder, position: Int) {

        val currentItem = yourWishlistList[position]
        holder.wishlistName.text = currentItem.userWishlist

    }

    override fun getItemCount(): Int {
        return yourWishlistList.size
    }

    class WishlistViewHolder(wishlistView: View): RecyclerView.ViewHolder(wishlistView){

        val wishlistName : TextView = wishlistView.findViewById(R.id.text_itemwish)

    }

}

