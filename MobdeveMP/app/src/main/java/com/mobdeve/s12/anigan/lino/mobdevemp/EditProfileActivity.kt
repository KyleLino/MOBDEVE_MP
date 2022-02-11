package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.User
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditProfileBinding
    lateinit var databaseReference: DatabaseReference
    lateinit var database: FirebaseDatabase

    var TAG = "EDITPROFILEACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("User")

        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var bundle = intent.extras
        var bundleusername = bundle?.getString("username")
        //var bundlename = bundle?.getString("name")
        //var bundlepassword = bundle?.getString("password")

        binding!!.textusername.hint = bundleusername
        //binding!!.textname.hint = bundlename
        //binding!!.textpassword.hint = bundlepassword
        //binding!!.textconfirmpassword.hint = bundlepassword

        binding.savebutton.setOnClickListener {

            val username = binding.textusername.text.toString()
            val name = binding.textname.text.toString()
            val password = binding.textpassword.text.toString()
            val confirmpassword = binding.textconfirmpassword.text.toString()

            if(username.isEmpty() || password.isEmpty() || name.isEmpty() || confirmpassword.isEmpty()){
                Log.i(TAG,"ETOOOOOOO")
                Toast.makeText(this, "empty field", Toast.LENGTH_SHORT).show()
            }
            else if (password != confirmpassword){
                Toast.makeText(this, "passwords not match", Toast.LENGTH_SHORT).show()
            }
            else{
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.

                        var isReal = false
                        var list = ArrayList<User>()
                        for (postsnapshot in dataSnapshot.children){

                            var value = postsnapshot.getValue<User>()

                            if (value!!.username == bundleusername ){
                                isReal = true
                                //value!!.name = name
                                //value!!.username = username
                                //value!!.password = password
                                var id = postsnapshot.key.toString()

                                //databaseReference.child(id).setValue(username)
                                databaseReference.child(id).child("username").setValue(username)
                                databaseReference.child(id).child("name").setValue(name)
                                databaseReference.child(id).child("password").setValue(password)

                            }

                            list.add(value!!)
                        }

                        if (isReal){
                            val gotoProfileActivity = Intent(applicationContext, ProfileActivity::class.java)

                            var bundle = Bundle()
                            bundle.putString("username", username)
                            bundle.putString("name", name)
                            bundle.putString("password", password)
                            gotoProfileActivity.putExtras(bundle)
                            startActivity(gotoProfileActivity)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Failed to read value
                    }
                })
                Toast.makeText(this, "incorrect username or password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.profileback.setOnClickListener {
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity ::class.java)
            var bundle = Bundle()
            bundle.putString("username", bundleusername)
            gotoProfileActivity.putExtras(bundle)
            startActivity(gotoProfileActivity )
        }


        binding.deletebutton.setOnClickListener {
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    var isReal = false
                    var id = ""
                    var list = ArrayList<User>()
                    for (postsnapshot in dataSnapshot.children){

                        var value = postsnapshot.getValue<User>()

                        if (value!!.username == bundleusername){
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
                        val gotoMainActivity = Intent(applicationContext, MainActivity::class.java)
                        startActivity(gotoMainActivity)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                }
            })
        }
    }
}