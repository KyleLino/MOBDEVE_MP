package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    var register: TextView? = null
    var username: EditText? = null
    var password: EditText? = null
    var buttonlogin: Button? = null
    var buttonloginfacebook: Button? = null

    var TAG = "MAINACTIVITY"

    lateinit var database: DatabaseReference
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!2")
    }

    override fun onStart() {
        super.onStart()


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        username = findViewById(R.id.textusername)
        password = findViewById(R.id.textpassword)

        buttonlogin = findViewById(R.id.login)
        buttonloginfacebook = findViewById(R.id.loginfacebook)

        register = findViewById(R.id.register)

        //REGISTER
        register!!.setOnClickListener{
            Log.i(TAG,"pressed register")

            val gotoRegisterActivity = Intent(applicationContext, RegisterActivity::class.java)

            //var bundle = Bundle()
            //bundle.putString("username", username!!.text.toString())
            //gotoRegisterActivity.putExtras(bundle)

            startActivity(gotoRegisterActivity)
        }

        //LOGIN
        buttonlogin!!.setOnClickListener{

            login()

            /*
            Log.i(TAG,"pressed login")
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity::class.java)

            var bundle = Bundle()
            bundle.putString("username", username!!.text.toString())

            gotoProfileActivity.putExtras(bundle)
            startActivity(gotoProfileActivity)*/
        }

        //LOGIN FB
        buttonloginfacebook!!.setOnClickListener{
            Log.i(TAG,"pressed login facebook")
        }

    }

    private fun login(){
        var username = binding.textusername.toString().trim()
        var password = binding.textpassword.toString().trim()

        if (username.isEmpty() || password.isEmpty()){
            Log.i(TAG,"empty field/s")
        }else{
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    var isReal = false
                    var list = ArrayList<User>()
                    for (postsnapshot in dataSnapshot.children){

                        var value = dataSnapshot.getValue<User>()

                        if (value!!.username == (username) && value!!.password == password){
                            isReal = true
                        }

                        list.add(value!!)
                    }

                    if (isReal){
                        val gotoProfileActivity = Intent(applicationContext, ProfileActivity::class.java)

                        var bundle = Bundle()
                        bundle.putString("username", binding.textusername.toString())
                        gotoProfileActivity.putExtras(bundle)
                        startActivity(gotoProfileActivity)
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value

                }
            })
        }
    }


    private fun isAccountExist(username: String, password: String){


    }
}