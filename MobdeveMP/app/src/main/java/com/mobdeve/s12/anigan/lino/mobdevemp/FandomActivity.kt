package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.ktx.getValue
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityFandomBinding
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.ValueEventListener

import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.User


class FandomActivity : AppCompatActivity() {

    lateinit var binding: ActivityFandomBinding
    lateinit var databaseReference: DatabaseReference
    lateinit var database: FirebaseDatabase

    var once = true
    var TAG = "FANDOMACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fandom)

        binding = ActivityFandomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bundle = intent.extras
        val bundleusername = bundle!!.getString("username")
        val bundlename = bundle!!.getString("name")
        val bundlepassword = bundle!!.getString("password")

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("User")

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

            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    var isReal = false
                    var list = ArrayList<User>()
                    for (postsnapshot in dataSnapshot.children) {

                        var value = postsnapshot.getValue<User>()

                        if (value!!.username == (bundleusername)) {

                            if (value.bp)
                                binding.checkBoxBP.isChecked = true
                            if (value.bts)
                                binding.checkBoxBTS.isChecked = true
                            if (value.exo)
                                binding.checkBoxEXO.isChecked = true
                            if (value.everglow)
                                binding.checkBoxEG.isChecked = true
                            if (value.twice)
                                binding.checkBoxTWICE.isChecked = true
                            if (value.got7)
                                binding.checkBoxG7.isChecked = true
                            if (value.mamamoo)
                                binding.checkBoxMMOO.isChecked = true
                            if (value.rv)
                                binding.checkBoxRV.isChecked = true
                            if (value.nct)
                                binding.checkBoxNCT.isChecked = true
                            if (value.wannaone)
                                binding.checkBoxW1.isChecked = true
                            if (value.others) {
                                binding.checkBoxOTHERS.isChecked = true
                            }
                        }
                        list.add(value!!)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                }
            })



        binding.profileback.setOnClickListener {
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity ::class.java)
            var bundle = Bundle()
            bundle.putString("username", bundleusername)
            //bundle.putString("name", bundlename)
            //bundle.putString("password", bundlepassword)
            gotoProfileActivity.putExtras(bundle)
            startActivity(gotoProfileActivity )
        }

        binding.buttonSAVE.setOnClickListener {

            BTS = binding.checkBoxBTS.isChecked
            EXO = binding.checkBoxEXO.isChecked
            NCT = binding.checkBoxNCT.isChecked
            WANNAONE = binding.checkBoxW1.isChecked
            GOT7 = binding.checkBoxG7.isChecked
            BP = binding.checkBoxBP.isChecked
            TWICE = binding.checkBoxTWICE.isChecked
            RV = binding.checkBoxRV.isChecked
            EG = binding.checkBoxEG.isChecked
            MMOO = binding.checkBoxMMOO.isChecked
            OTHERS = binding.checkBoxOTHERS.isChecked

            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    //var isReal = false
                    var list = ArrayList<User>()
                    for (postsnapshot in dataSnapshot.children){

                        var value = postsnapshot.getValue<User>()

                        if (value!!.username == bundleusername ){
                            //isReal = true
                            //value!!.name = name
                            //value!!.username = username
                            //value!!.password = password
                            var id = postsnapshot.key.toString()

                            //IT LOOOPPPSSS IF once is not there
                            if(once){
                                databaseReference.child(id).child("bts").setValue(BTS)
                                databaseReference.child(id).child("exo").setValue(EXO)
                                databaseReference.child(id).child("wannaone").setValue(WANNAONE)
                                databaseReference.child(id).child("got7").setValue(GOT7)
                                databaseReference.child(id).child("nct").setValue(NCT)

                                databaseReference.child(id).child("bp").setValue(BP)
                                databaseReference.child(id).child("twice").setValue(TWICE)
                                databaseReference.child(id).child("rv").setValue(RV)
                                databaseReference.child(id).child("everglow").setValue(EG)
                                databaseReference.child(id).child("mamamoo").setValue(MMOO)

                                databaseReference.child(id).child("others").setValue(OTHERS)
                                Log.i(TAG, "here!")
                            }
                            once = false
                            //databaseReference.child(id).setValue(username)

                        }
                        list.add(value!!)
                    }

                        val gotoProfileActivity = Intent(applicationContext, ProfileActivity ::class.java)
                        var bundle = Bundle()
                        bundle.putString("username", bundleusername)
                        bundle.putString("name", bundlename)
                        bundle.putString("password", bundlepassword)
                        gotoProfileActivity.putExtras(bundle)
                        startActivity(gotoProfileActivity )

                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                }
            })

            Toast.makeText(this, "fandoms saved", Toast.LENGTH_SHORT).show()
        }
    }

}




