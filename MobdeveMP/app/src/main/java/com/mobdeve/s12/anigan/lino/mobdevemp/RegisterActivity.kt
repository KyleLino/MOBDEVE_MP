package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.User
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding


    lateinit var databaseReference: DatabaseReference
    lateinit var database: FirebaseDatabase

    var buttonregister: Button? = null
    var buttonregisterfacebook: Button? = null

    var TAG = "REGISTERACTIVITY"
    var string = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonregister = findViewById(R.id.register)
        buttonregisterfacebook = findViewById(R.id.registerwithfacebook)

        binding.register.setOnClickListener {
            Log.i(TAG, "pressed register")
            
            val username = binding.textusername.text.toString()
            val name = binding.textname.text.toString()
            val password = binding.textpassword.text.toString()
            val confirmpassword = binding.textconfirmpassword.text.toString()

            if (username.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty() && password == confirmpassword) {





                database = FirebaseDatabase.getInstance()
                databaseReference = database.getReference("User")
                var real = false

                //chech if user exist
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        var list = ArrayList<User>()
                        for (postsnapshot in dataSnapshot.children){
                            var value = postsnapshot.getValue<User>()
                            if (value!!.username == username){
                                real = true
                                Log.i(TAG, "USER EXIST")
                                break
                            }
                            list.add(value!!)
                        }
                        if (real){
                            string = "username exist"
                        }else{
                            databaseReference = FirebaseDatabase.getInstance().getReference("User")
                            var id = databaseReference.push().key.toString()
                            val User = User(username, name, password, false,false,false,false,false,false,false,false,false,false,false)
                            databaseReference.child(id).setValue(User).addOnSuccessListener {

                                binding.textusername.text.clear()
                                binding.textname.text.clear()
                                binding.textpassword.text.clear()
                                binding.textconfirmpassword.text.clear()

                                //Toast.makeText(TAG, "user registered", Toast.LENGTH_SHORT).show()
                                string = "user registered"

                                val gotoMainActivity = Intent(applicationContext, MainActivity::class.java)
                                startActivity(gotoMainActivity)

                            }.addOnFailureListener {
                                Log.i(TAG, "here!")
                                //Toast.makeText(this, "user register FAILED", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        // Failed to read value
                    }
                })
            } else if (password != confirmpassword) {
                Toast.makeText(
                    this,
                    "password and confirm password does not match",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, "fill up all fields", Toast.LENGTH_SHORT).show()
            }
            Toast.makeText(this, "$string", Toast.LENGTH_SHORT).show()
        }

        buttonregisterfacebook!!.setOnClickListener {
            Log.i(TAG, "pressed register fb")
        }
    }


    override fun onStart() {
        super.onStart()
    }

}