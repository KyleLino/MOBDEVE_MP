package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.OtherUserAdapter
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.OtherUsers
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.YourItems
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
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        otherUserList = arrayListOf()
        getUserData()

        binding!!.profileback!!.setOnClickListener {
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity ::class.java)
            var bundle = Bundle()
            bundle.putString("username", bundleusername)
            gotoProfileActivity.putExtras(bundle)
            startActivity(gotoProfileActivity )
        }
    }


    private fun getUserData() {

        var bundle = intent.extras
        var bundleusername = bundle!!.getString("username")

        databaseReference = FirebaseDatabase.getInstance().getReference("User")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(OtherUsers::class.java)
                        if (user != null) {
                            if(user.username != bundleusername){
                                otherUserList.add(user!!)
                            }
                        }
                    }
                    userRecyclerView.adapter = OtherUserAdapter(applicationContext,otherUserList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}