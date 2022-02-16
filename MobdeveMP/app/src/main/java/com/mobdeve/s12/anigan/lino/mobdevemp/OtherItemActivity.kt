package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.UserItem
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityAddItemBinding
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityOtherItemBinding

class OtherItemActivity : AppCompatActivity() {

    lateinit var binding: ActivityOtherItemBinding
    lateinit var databaseReference: DatabaseReference
    lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_item)

        binding = ActivityOtherItemBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val bundle = intent.extras
        val othername = bundle!!.getString("otheritemname")
        val otherprice = bundle!!.getString("otheritemprice")
        val otherdescription = bundle!!.getString("otheritemdescription")
        var otherbundleusername = bundle!!.getString("otheritemowner")

        binding!!.offerItemname.text = "Name: $othername"
        binding!!.offerItemprice.text = "Price: $otherprice"
        binding!!.offerItemdescription.text = "Description: $otherdescription"

        binding.buttonTradeoffer.setOnClickListener {
            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("UserItem")

            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    var isReal = false
                    var otheritemid = ""
                    var list = ArrayList<UserItem>()
                    for (postsnapshot in dataSnapshot.children){

                        var value = postsnapshot.getValue<UserItem>()

                        if (value!!.itemName == othername && value!!.itemPrice == otherprice && value!!.itemDescription == otherdescription && value!!.itemOwner == otherbundleusername){
                            isReal = true
                            otheritemid = postsnapshot.key.toString()
                        }

                        list.add(value!!)
                    }

                    if (isReal){
                        //databaseReference.child(id).removeValue()
                        val gotoPickTradeActivity = Intent(applicationContext, PickTradeActivity::class.java)
                        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.apply{
                            putString("otheritemID", otheritemid)
                        }.apply()
                        startActivity(gotoPickTradeActivity)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                }
            })
        }
    }
}