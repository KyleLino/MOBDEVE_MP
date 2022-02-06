package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.YourItemAdapter
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.YourItems
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityViewCollectionBinding
import com.mobdeve.s12.anigan.lino.mobdevemp.model.Item

class ViewCollectionActivity : AppCompatActivity() {

    var binding: ActivityViewCollectionBinding? = null
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var yourItemList: ArrayList<YourItems>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityViewCollectionBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        userRecyclerView = findViewById(R.id.your_items)
        userRecyclerView.layoutManager=LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        yourItemList = arrayListOf<YourItems>()
        getUserData()

        binding!!.additem!!.setOnClickListener {
            val gotoAddItemActivity = Intent(applicationContext, AddItemActivity::class.java)

            startActivity(gotoAddItemActivity)
        }

        binding!!.profileback!!.setOnClickListener {
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity ::class.java)
            startActivity(gotoProfileActivity )
        }
    }

    private fun getUserData(){

        databaseReference = FirebaseDatabase.getInstance().getReference("UserItem")

        databaseReference.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for(itemSnapshot in snapshot.children){

                        val item = itemSnapshot.getValue(YourItems::class.java)
                        yourItemList.add(item!!)

                    }
                    userRecyclerView.adapter = YourItemAdapter(yourItemList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}