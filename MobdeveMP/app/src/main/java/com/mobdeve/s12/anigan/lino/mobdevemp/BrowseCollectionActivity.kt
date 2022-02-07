package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.OtherUserAdapter
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.OtherUsers
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityBrowseCollectionBinding


class BrowseCollectionActivity : AppCompatActivity() {

    var binding: ActivityBrowseCollectionBinding? = null
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var otherUserList: ArrayList<OtherUsers>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityBrowseCollectionBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var bundle = intent.extras
        var bundleusername = bundle!!.getString("username")

        userRecyclerView = findViewById(R.id.other_items)
        userRecyclerView.layoutManager= LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        otherUserList = arrayListOf<OtherUsers>()
        getUserData()

        binding!!.profileback!!.setOnClickListener {
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity ::class.java)
            startActivity(gotoProfileActivity )
        }
    }


    private fun getUserData(){

        databaseReference = FirebaseDatabase.getInstance().getReference("User")
        var bundle = intent.extras
        var bundleusername = bundle!!.getString("username")

        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snap: DataSnapshot) {

                if(snap.exists()){
                    for(userSnapshot in snap.children){

                        val otherUser = userSnapshot.getValue(OtherUsers::class.java)
                        otherUserList.add(otherUser!!)

                    }
                    userRecyclerView.adapter = OtherUserAdapter(otherUserList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}