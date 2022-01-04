package com.mobdeve.s12.lino.kyle.mp_phase2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.mobdeve.s12.lino.kyle.mp_phase2.R
import com.mobdeve.s12.lino.kyle.mp_phase2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    var register: TextView? = null
    var username: EditText? = null
    var password: EditText? = null
    var buttonlogin: Button? = null
    var buttonloginfacebook: Button? = null

    var TAG = "MAINACTIVITY"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
            Log.i(TAG,"pressed login")
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity::class.java)

            var bundle = Bundle()
            bundle.putString("username", username!!.text.toString())

            gotoProfileActivity.putExtras(bundle)
            startActivity(gotoProfileActivity)
        }

        //LOGIN FB
        buttonloginfacebook!!.setOnClickListener{
            Log.i(TAG,"pressed login facebook")
        }

    }
}