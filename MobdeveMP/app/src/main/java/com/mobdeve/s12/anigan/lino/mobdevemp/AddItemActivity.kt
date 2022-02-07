package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityAddItemBinding


class AddItemActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddItemBinding
    lateinit var database: DatabaseReference

    var TAG = "ADDITEMACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
    }

    override fun onStart() {
        super.onStart()

        var bundle = intent.extras

        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.addbutton!!.setOnClickListener{

            val name = binding.itemnameinput.text.toString()
            val price = binding.itempriceinput.text.toString()
            val description = binding.itemdescriptioninput.text.toString()

            var owner = bundle!!.getString("username")
            Log.i(TAG,"$owner")

            if (name != "" && price != "" && description != ""){

                database = FirebaseDatabase.getInstance().getReference("UserItem")
                val UserItem = owner?.let { it1 -> UserItem(name,price,description, it1) }
                database.child(name).setValue(UserItem).addOnSuccessListener {

                    Log.i(TAG,"here")

                    binding.itemnameinput.text.clear()
                    binding.itemdescriptioninput.text.clear()
                    binding.itempriceinput.text.clear()

                    Toast.makeText(this, "user item created", Toast.LENGTH_SHORT).show()

                    val gotoViewCollectionActivity = Intent(applicationContext, ViewCollectionActivity::class.java)
                    var bundle = Bundle()
                    bundle.putString("username", owner)
                    gotoViewCollectionActivity.putExtras(bundle)
                    startActivity(gotoViewCollectionActivity)

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
            startActivity(gotoProfileActivity )
        }
    }
}