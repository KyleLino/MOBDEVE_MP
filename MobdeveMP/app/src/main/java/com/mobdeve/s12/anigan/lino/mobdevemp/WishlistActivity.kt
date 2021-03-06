package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.YourWishlist
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.YourWishlistAdapter
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityWishlistBinding

class WishlistActivity : AppCompatActivity() {

    lateinit var binding: ActivityWishlistBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var yourWishlistList: ArrayList<YourWishlist>
    var once = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        userRecyclerView = findViewById(R.id.your_wishlist)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        yourWishlistList = arrayListOf<YourWishlist>()


        getUserData()



        var bundle = intent.extras
        var bundleusername = bundle!!.getString("username")

        binding.addwishlist.setOnClickListener {
            val gotoAddWishListActivity =
                Intent(applicationContext, AddWishListActivity::class.java)
            var bundle = Bundle()
            bundle.putString("username", bundleusername)
            gotoAddWishListActivity.putExtras(bundle)
            startActivity(gotoAddWishListActivity)
        }

        binding!!.profileback!!.setOnClickListener {
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity ::class.java)
            var bundle = Bundle()
            bundle.putString("username", bundleusername)
            gotoProfileActivity.putExtras(bundle)
            startActivity(gotoProfileActivity )
        }

    }

    private fun getUserData() {

        yourWishlistList.clear()
        var bundle = intent.extras
        var bundleusername = bundle!!.getString("username")

        databaseReference = FirebaseDatabase.getInstance().getReference("UserWishListItem")
        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (wishlistSnapshot in snapshot.children) {
                        val yourWishlist = wishlistSnapshot.getValue(YourWishlist::class.java)
                        if (yourWishlist != null) {
                            if(yourWishlist.itemOwner == bundleusername){
                                yourWishlistList.add(yourWishlist!!)

                                //yourWishlistList.remove(yourWishlist!!)
                            }
                        }
                    }
                    userRecyclerView.adapter = YourWishlistAdapter(applicationContext,yourWishlistList)

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }

    override fun onRestart() {
        super.onRestart()
        recreate()
    }
}