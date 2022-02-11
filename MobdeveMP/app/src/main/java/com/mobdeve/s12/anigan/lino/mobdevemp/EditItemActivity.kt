package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.UserItem
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityEditItemBinding

class EditItemActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditItemBinding
    lateinit var databaseReference: DatabaseReference
    lateinit var database: FirebaseDatabase

    var TAG = "EDITITEMACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item)
    }

    override fun onStart() {
        super.onStart()

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("UserItem")

        binding = ActivityEditItemBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val bundle = intent.extras
        var bundlename = bundle!!.getString("itemname")
        var bundleprice = bundle!!.getString("itemprice")
        var bundledescription = bundle!!.getString("itemdescription")
        var bundleusername = bundle!!.getString("itemowner")

        binding.itemnameinput.hint = bundlename
        binding.itempriceinput.hint = bundleprice
        binding.itemdescriptioninput.hint = bundledescription


        binding.donebutton.setOnClickListener {

            val itemname = binding.itemnameinput.text.toString()
            val itemprice = binding.itempriceinput.text.toString()
            val itemdescription = binding.itemdescriptioninput.text.toString()

            if (itemname.isEmpty() || itemprice.isEmpty() || itemdescription.isEmpty()) {
                Log.i(TAG, "ETOOOOOOO")
                Toast.makeText(this, "empty field", Toast.LENGTH_SHORT).show()
            } else {
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.

                        var isReal = false
                        var list = ArrayList<UserItem>()
                        for (postsnapshot in dataSnapshot.children) {

                            var value = postsnapshot.getValue<UserItem>()

                            if (value!!.itemOwner == bundleusername && value!!.itemName == bundlename && value!!.itemDescription == bundledescription && value!!.itemPrice == bundleprice) {
                                isReal = true
                                //value!!.name = name
                                //value!!.username = username
                                //value!!.password = password
                                var id = postsnapshot.key.toString()

                                //databaseReference.child(id).setValue(username)
                                databaseReference.child(id).child("itemName").setValue(itemname)
                                databaseReference.child(id).child("itemPrice").setValue(itemprice)
                                databaseReference.child(id).child("itemDescription").setValue(itemdescription)

                            }

                            list.add(value!!)
                        }

                        if (isReal) {
                            val gotoViewItemActivity =
                                Intent(applicationContext, ViewItemActivity::class.java)

                            var bundle = Bundle()
                            bundle.putString("itemname", itemname)
                            bundle.putString("itemprice", itemprice)
                            bundle.putString("itemdescription", itemdescription)
                            bundle.putString("itemowner", bundleusername)
                            gotoViewItemActivity.putExtras(bundle)
                            startActivity(gotoViewItemActivity)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Failed to read value
                    }
                })

            }

            binding!!.profileback!!.setOnClickListener {
                val gotoProfileActivity = Intent(applicationContext, ProfileActivity::class.java)
                var bundle = Bundle()
                bundle.putString("username", bundleusername)
                gotoProfileActivity.putExtras(bundle)
                startActivity(gotoProfileActivity)
            }
        }
    }
}