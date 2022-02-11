package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityViewItemBinding

class ViewItemActivity : AppCompatActivity() {

    var binding: ActivityViewItemBinding? = null
    lateinit var databaseReference: DatabaseReference
    lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewItemBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val bundle = intent.extras
        val name = bundle!!.getString("itemname")
        val price = bundle!!.getString("itemprice")
        val description = bundle!!.getString("itemdescription")
        var bundleusername = bundle!!.getString("itemowner")


        binding!!.itemname.text = "Name: $name"
        binding!!.itemprice.text = "Price: $price"
        binding!!.itemdescription.text = "Description: $description"

        val buttonEdit = binding!!.editbutton
        val buttonDelete = binding!!.deletebutton

        buttonEdit.setOnClickListener{
            val gotoEditItemActivity = Intent(applicationContext, EditItemActivity::class.java)
            var bundle = Bundle()
            bundle.putString("itemowner", bundleusername)
            bundle.putString("itemname", name)
            bundle.putString("itemprice", price)
            bundle.putString("itemdescription", description)
            gotoEditItemActivity.putExtras(bundle)
            startActivity(gotoEditItemActivity)
        }

        buttonDelete.setOnClickListener{
            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("UserItem")

            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    var isReal = false
                    var id = ""
                    var list = ArrayList<UserItem>()
                    for (postsnapshot in dataSnapshot.children){

                        var value = postsnapshot.getValue<UserItem>()

                        if (value!!.itemName == name && value!!.itemPrice == price && value!!.itemDescription == description && value!!.itemOwner == bundleusername){
                            isReal = true
                            //value!!.name = name
                            //value!!.username = username
                            //value!!.password = password
                            id = postsnapshot.key.toString()
                            //databaseReference.child(id).setValue(username)
                        }

                        list.add(value!!)
                    }

                    if (isReal){
                        databaseReference.child(id).removeValue()
                        val gotoViewCollectionActivity = Intent(applicationContext, ViewCollectionActivity::class.java)
                        var bundle = Bundle()
                        bundle.putString("username", bundleusername)
                        gotoViewCollectionActivity.putExtras(bundle)
                        startActivity(gotoViewCollectionActivity)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                }
            })
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