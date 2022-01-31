package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var database: DatabaseReference

    var buttonregister: Button? = null
    var buttonregisterfacebook: Button? = null

    var TAG = "REGISTERACTIVITY"

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

            database = FirebaseDatabase.getInstance().getReference("User")
            val User = User(username, name, password)
            database.child(username).setValue(User).addOnSuccessListener {
                if (username != "" && name != "" && password != "" && password == confirmpassword) {

                    database = FirebaseDatabase.getInstance().getReference("User")
                    val User = User(username, name, password)
                    database.child(username).setValue(User).addOnSuccessListener {

                        binding.textusername.text.clear()
                        binding.textname.text.clear()
                        binding.textpassword.text.clear()
                        binding.textconfirmpassword.text.clear()

                        Toast.makeText(this, "user registered", Toast.LENGTH_SHORT).show()

                        val gotoMainActivity = Intent(applicationContext, MainActivity::class.java)
                        startActivity(gotoMainActivity)

                    }.addOnFailureListener {
                        Log.i(TAG, "here!")
                        Toast.makeText(this, "user register FAILED", Toast.LENGTH_SHORT).show()
                    }
                } else if (password != confirmpassword) {
                    Toast.makeText(this, "password and confirm password does not match", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "fill up all fields", Toast.LENGTH_SHORT).show()
                }



            }

            buttonregisterfacebook!!.setOnClickListener {
                Log.i(TAG, "pressed register fb")
            }
        }


    }

    override fun onStart() {
        super.onStart()


    }

}