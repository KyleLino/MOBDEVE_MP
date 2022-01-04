package com.mobdeve.s12.lino.kyle.mp_phase2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.mobdeve.s12.lino.kyle.mp_phase2.R
import com.mobdeve.s12.lino.kyle.mp_phase2.databinding.ActivityProfileBinding
import com.mobdeve.s12.lino.kyle.mp_phase2.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    var binding: ActivityRegisterBinding? = null
    var username: EditText? = null
    var name: EditText? = null
    var password: EditText? = null
    var confirmpassword: EditText? = null
    var buttonregister: Button? = null
    var buttonregisterfacebook: Button? = null

    var TAG = "REGISTERACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    override fun onStart() {
        super.onStart()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        username = findViewById(R.id.textusername)
        name = findViewById(R.id.textname)
        password = findViewById(R.id.textpassword)
        confirmpassword = findViewById(R.id.textconfirmpassword)
        buttonregister = findViewById(R.id.register)
        buttonregisterfacebook = findViewById(R.id.registerwithfacebook)

        buttonregister!!.setOnClickListener{
            Log.i(TAG,"pressed register")

            val gotoMainActivity = Intent(applicationContext, MainActivity::class.java)

            startActivity(gotoMainActivity)
        }

        buttonregisterfacebook!!.setOnClickListener{
            Log.i(TAG,"pressed register fb")
        }
    }
}