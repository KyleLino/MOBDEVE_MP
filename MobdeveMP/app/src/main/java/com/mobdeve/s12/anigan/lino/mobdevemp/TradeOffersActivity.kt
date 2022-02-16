package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.OtherUserAdapter
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.OtherUsers
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.TradeAdapter
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.UserItem
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityFandomBinding
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityProfileBinding
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityTradeOffersBinding

class TradeOffersActivity : AppCompatActivity() {

    lateinit var binding: ActivityTradeOffersBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var otherItemList: ArrayList<UserItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trade_offers)

        var bundle = intent.extras
        val bundleusername = bundle!!.getString("username")

        binding = ActivityTradeOffersBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        userRecyclerView = findViewById(R.id.senttradeoffers)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        otherItemList = arrayListOf()
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

        databaseReference = FirebaseDatabase.getInstance().getReference("UserItem")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(UserItem::class.java)
                        if (user != null) {
                            if(user.itemOwner == bundleusername){
                                otherItemList.add(user!!)
                            }
                        }
                    }
                    userRecyclerView.adapter = TradeAdapter(applicationContext,otherItemList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}