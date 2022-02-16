package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.SelectTradeOfferAdapter
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.UserItem
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.YourItemAdapter
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.YourItems

class PickTradeActivity : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var yourItemList: ArrayList<YourItems>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_trade)

        userRecyclerView = findViewById(R.id.picktrade_recyclerView)
        userRecyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        userRecyclerView.setHasFixedSize(true)

        yourItemList = arrayListOf<YourItems>()
        getUserData()
    }

    private fun getUserData(){

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedloggedin = sharedPreferences.getString("loggedinuser", null)

        databaseReference = FirebaseDatabase.getInstance().getReference("UserItem")
        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(itemSnapshot in snapshot.children){
                        val item = itemSnapshot.getValue(YourItems::class.java)
                        if (item != null) {
                            if (item.itemOwner == savedloggedin){
                                yourItemList.add(item!!)
                            }
                        }
                    }
                    userRecyclerView.adapter = SelectTradeOfferAdapter(applicationContext, yourItemList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}