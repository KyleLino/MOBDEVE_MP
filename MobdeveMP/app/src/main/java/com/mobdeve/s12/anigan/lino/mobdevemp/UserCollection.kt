package com.mobdeve.s12.anigan.lino.mobdevemp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityProfileBinding

class UserCollection : AppCompatActivity() {

    var chatuser: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_collection)
    }


}