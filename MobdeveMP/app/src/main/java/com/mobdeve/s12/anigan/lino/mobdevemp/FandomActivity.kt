package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.mobdeve.s12.anigan.lino.mobdevemp.R
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityFandomBinding
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.ValueEventListener

import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityProfileBinding


class FandomActivity : AppCompatActivity() {

    lateinit var binding: ActivityFandomBinding

    var TAG = "FANDOMACTIVITY"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fandom)

        binding = ActivityFandomBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var bundle = intent.extras
        val bundleusername = bundle!!.getString("username")

        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("User")
        var name = "null"

        var BTS = false
        var EXO = false
        var NCT = false
        var WANNAONE = false
        var GOT7 = false

        var BP = false
        var TWICE = false
        var RV = false
        var EG = false
        var MMOO = false

        var OTHERS = false

        binding!!.profileback!!.setOnClickListener {
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity ::class.java)
            var bundle = Bundle()
            bundle.putString("username", bundleusername)
            gotoProfileActivity.putExtras(bundle)
            startActivity(gotoProfileActivity )
        }

        binding!!.buttonSAVE!!.setOnClickListener {
            Toast.makeText(this, "fandoms saved", Toast.LENGTH_SHORT).show()
        }

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                var isReal = false
                var list = ArrayList<User>()
                for (postsnapshot in dataSnapshot.children) {

                    var value = postsnapshot.getValue<User>()

                    if (value!!.username == (bundleusername)) {

                        if (value.bp == 1)
                            binding!!.checkBoxBP.isChecked = true
                        if (value.bts == 1)
                            binding!!.checkBoxBTS.isChecked = true
                        if (value.exo == 1)
                            binding!!.checkBoxEXO.isChecked = true
                        if (value.everglow == 1)
                            binding!!.checkBoxEG.isChecked = true
                        if (value.twice == 1)
                            binding!!.checkBoxTWICE.isChecked = true
                        if (value.got7 == 1)
                            binding!!.checkBoxG7.isChecked = true
                        if (value.mamamoo == 1)
                            binding!!.checkBoxMMM.isChecked = true
                        if (value.rv == 1)
                            binding!!.checkBoxRV.isChecked = true
                        if (value.nct == 1)
                            binding!!.checkBoxNCT.isChecked = true
                        if (value.wannaone == 1)
                            binding!!.checkBoxW1.isChecked = true
                        if (value.others == 1){
                            binding!!.checkBoxOTHERS.isChecked = true
                        }
                    }

                    list.add(value!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })
    }

}




