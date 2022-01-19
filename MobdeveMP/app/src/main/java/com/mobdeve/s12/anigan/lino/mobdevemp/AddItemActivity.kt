package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityAddItemBinding


class AddItemActivity : AppCompatActivity() {

    var binding: ActivityAddItemBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
    }

    override fun onStart() {
        super.onStart()

        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.addbutton!!.setOnClickListener{
            val gotoViewCollectionActivity = Intent(applicationContext, ViewCollectionActivity::class.java)

            startActivity(gotoViewCollectionActivity)
        }

        binding!!.profileback!!.setOnClickListener {
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity ::class.java)
            startActivity(gotoProfileActivity )
        }
    }
}