package com.mobdeve.s12.anigan.lino.mobdevemp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.UserItem
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.UserItemAdapter
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.UserWishList
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.UserWishlistAdapter
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityViewOtherUserProfileBinding

class ViewOtherUserProfileActivity : AppCompatActivity() {

    var binding: ActivityViewOtherUserProfileBinding? = null
    private lateinit var databaseReference: DatabaseReference
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var wishlistRecyclerView: RecyclerView
    private lateinit var userItemList: ArrayList<UserItem>
    private lateinit var userWishlistList: ArrayList<UserWishList>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewOtherUserProfileBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var bundle = intent.extras
        var bundleusername = bundle!!.getString("username")

        itemRecyclerView = findViewById(R.id.other_user_items)
        wishlistRecyclerView = findViewById(R.id.other_user_wishlist)

        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        wishlistRecyclerView.layoutManager = LinearLayoutManager(this)

        itemRecyclerView.setHasFixedSize(true)
        wishlistRecyclerView.setHasFixedSize(true)

        userItemList = arrayListOf()
        userWishlistList = arrayListOf()

        getUserItemData()
        getUserWishlistData()
    }

    private fun getUserWishlistData() {
        var bundle = intent.extras
        var bundleusername = bundle!!.getString("username")

        databaseReference = FirebaseDatabase.getInstance().getReference("UserWishListItem")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val wishlist = userSnapshot.getValue(UserWishList::class.java)
                        if (wishlist != null) {
                            //if(wishlist.username != bundleusername){
                                userWishlistList.add(wishlist!!)
                            //}
                        }
                    }
                    wishlistRecyclerView.adapter = UserWishlistAdapter(userWishlistList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun getUserItemData() {
        var bundle = intent.extras
        var bundleusername = bundle!!.getString("username")

        databaseReference = FirebaseDatabase.getInstance().getReference("UserItem")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(UserItem::class.java)
                        if (user != null) {
                            if(user.itemOwner != bundleusername){
                                userItemList.add(user!!)
                            }
                        }
                    }
                    itemRecyclerView.adapter = UserItemAdapter(userItemList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}