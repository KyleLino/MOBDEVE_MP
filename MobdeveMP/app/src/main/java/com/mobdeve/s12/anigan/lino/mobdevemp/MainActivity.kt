package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.User
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityMainBinding
import kotlin.math.sign

class MainActivity : AppCompatActivity() {

    var TAG = "MAINACTIVITY"
    private val rc_google = 1000

    private lateinit var auth: FirebaseAuth

    lateinit var databaseReference: DatabaseReference
    lateinit var database: FirebaseDatabase
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("User")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        auth = Firebase.auth

        //REGISTER
        binding.register.setOnClickListener{
            Log.i(TAG,"pressed register")

            val gotoRegisterActivity = Intent(applicationContext, RegisterActivity::class.java)

            //var bundle = Bundle()
            //bundle.putString("username", username!!.text.toString())
            //gotoRegisterActivity.putExtras(bundle)

            startActivity(gotoRegisterActivity)
        }


        //LOGIN FB
        binding.logingoogle.setOnClickListener{
            Toast.makeText(this, "DITO", Toast.LENGTH_SHORT).show()
            Log.i(TAG,"pressed login facebook")

            val gotoGoogleLoginActivity = Intent(applicationContext, GoogleLoginActivity::class.java)

            //var bundle = Bundle()
            //bundle.putString("username", username!!.text.toString())
            //gotoRegisterActivity.putExtras(bundle)

            startActivity(gotoGoogleLoginActivity)
        }


        //LOGIN
        binding.loginbutton.setOnClickListener{

            val username = binding.textusername.text.toString()
            var password = binding.textpassword.text.toString()

            var name: String = ""
            //var password: String = ""

            if(username.isEmpty() || password.isEmpty()){
                Log.i(TAG,"ETOOOOOOO")
                Toast.makeText(this, "empty field", Toast.LENGTH_SHORT).show()
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

                            if (value!!.username == (username) && value!!.password == password){
                                isReal = true
                                name = value!!.name
                                password = value!!.password
                            }

                            list.add(value!!)
                        }

                        if (isReal){
                            val gotoProfileActivity = Intent(applicationContext, ProfileActivity::class.java)

                            var bundle = Bundle()
                            bundle.putString("username", binding.textusername.text.toString())
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
    }


    private fun login(){

    }


    private fun isAccountExist(username: String, password: String){


    }
}