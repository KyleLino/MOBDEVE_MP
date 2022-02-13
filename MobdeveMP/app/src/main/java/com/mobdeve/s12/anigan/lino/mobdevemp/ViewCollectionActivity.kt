package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.YourItemAdapter
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.YourItems
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityViewCollectionBinding

class ViewCollectionActivity : AppCompatActivity() {

    var binding: ActivityViewCollectionBinding? = null
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var yourItemList: ArrayList<YourItems>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityViewCollectionBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var bundle = intent.extras
        var bundleusername = bundle!!.getString("username")

        userRecyclerView = findViewById(R.id.your_items)
        userRecyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        userRecyclerView.setHasFixedSize(true)

        yourItemList = arrayListOf()
        getUserData()

        binding!!.additem!!.setOnClickListener {
            val gotoAddItemActivity = Intent(applicationContext, AddItemActivity::class.java)
            var bundle = Bundle()
            bundle.putString("username", bundleusername)
            gotoAddItemActivity.putExtras(bundle)
            startActivity(gotoAddItemActivity)
        }

        binding!!.profileback!!.setOnClickListener {
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity ::class.java)
            var bundle = Bundle()
            bundle.putString("username", bundleusername)
            gotoProfileActivity.putExtras(bundle)
            startActivity(gotoProfileActivity )
        }
    }

    private fun getUserData(){

        var bundle = intent.extras
        var bundleusername = bundle!!.getString("username")

        databaseReference = FirebaseDatabase.getInstance().getReference("UserItem")
        databaseReference.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(itemSnapshot in snapshot.children){
                        val item = itemSnapshot.getValue(YourItems::class.java)
                        if (item != null) {
                            if (item.itemOwner == bundleusername){
                                yourItemList.add(item!!)
                            }
                        }
                    }
                    userRecyclerView.adapter = YourItemAdapter(applicationContext,yourItemList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}