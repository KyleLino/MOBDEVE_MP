package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityEditItemBinding

class EditItemActivity : AppCompatActivity() {

    var binding: ActivityEditItemBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item)
    }

    override fun onStart() {
        super.onStart()

        binding = ActivityEditItemBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val bundle = intent.extras
        var name = bundle!!.getString("itemname")
        var price = bundle!!.getString("itemprice")
        var description = bundle!!.getString("itemdescription")
        var bundleusername = bundle!!.getString("itemowner")

        binding!!.donebutton!!.setOnClickListener{
            val gotoViewCollectionActivity = Intent(applicationContext, ViewCollectionActivity::class.java)

            val bundle = Bundle()
            //bundle.putString("name", binding!!.itemnameinput!!.toString())
            //bundle.putString("price", binding!!.itempriceinput!!.toString())
            //bundle.putString("description", binding!!.itemdescriptioninput!!.toString())
            bundle.putString("username", bundleusername)
            gotoViewCollectionActivity.putExtras(bundle)
            startActivity(gotoViewCollectionActivity)
        }

        binding!!.profileback!!.setOnClickListener {
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity ::class.java)
            var bundle = Bundle()
            bundle.putString("username", bundleusername)
            gotoProfileActivity.putExtras(bundle)
            startActivity(gotoProfileActivity )
        }
    }
}