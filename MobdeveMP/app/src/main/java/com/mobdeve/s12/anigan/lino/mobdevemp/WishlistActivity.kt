package com.mobdeve.s12.anigan.lino.mobdevemp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.OtherUserAdapter
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.OtherUsers
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.YourWishlist
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.YourWishlistAdapter
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityBrowseCollectionBinding
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityWishlistBinding

class WishlistActivity : AppCompatActivity() {

    var binding: ActivityWishlistBinding? = null
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var yourWishlistList: ArrayList<YourWishlist>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        userRecyclerView = findViewById(R.id.your_wishlist)
        userRecyclerView.layoutManager= LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        yourWishlistList = arrayListOf<YourWishlist>()
        getUserData()

    }

    private fun getUserData() {

        databaseReference = FirebaseDatabase.getInstance().getReference("UserWishListItem")

        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snap: DataSnapshot) {

                if(snap.exists()){
                    for(wishlistSnapshot in snap.children){

                        val yourWishlist = wishlistSnapshot.getValue(YourWishlist::class.java)
                        yourWishlistList.add(yourWishlist!!)

                    }
                    userRecyclerView.adapter = YourWishlistAdapter(yourWishlistList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }


}