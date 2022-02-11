package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.UserWishList
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityAddWishListBinding

class AddWishListActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddWishListBinding
    lateinit var database: DatabaseReference

    var TAG = "ADDWISHLISTACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_wish_list)

        var bundle = intent.extras
        var bundleusername = bundle!!.getString("username")

        binding = ActivityAddWishListBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.addbutton!!.setOnClickListener{

            val name = binding.itemnameinput.text.toString()

            var owner = bundle!!.getString("username")
            Log.i(TAG,"$owner")

            if (name != ""){

                database = FirebaseDatabase.getInstance().getReference("UserWishListItem")
                val UserWishList = owner?.let { it1 -> UserWishList(name, it1) }
                var id = database.push().key.toString()
                database.child(id).setValue(UserWishList).addOnSuccessListener {

                    Log.i(TAG,"here")

                    binding.itemnameinput.text.clear()

                    Toast.makeText(this, "user wishlist item created", Toast.LENGTH_SHORT).show()

                    val gotoWishlistActivity = Intent(applicationContext, WishlistActivity::class.java)
                    var bundle = Bundle()
                    bundle.putString("username", owner)
                    gotoWishlistActivity.putExtras(bundle)
                    startActivity(gotoWishlistActivity)

                }.addOnFailureListener {

                    Log.i(TAG,"here!")
                    Toast.makeText(this, "user register FAILED", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "fill up all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding!!.profileback!!.setOnClickListener {
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity ::class.java)
            var bundle = Bundle()
            bundle.putString("username", bundleusername)
            gotoProfileActivity.putExtras(bundle)
            startActivity(gotoProfileActivity )
        }
    }
}