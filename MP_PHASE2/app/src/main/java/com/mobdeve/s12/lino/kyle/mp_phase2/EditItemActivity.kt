package com.mobdeve.s12.lino.kyle.mp_phase2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s12.lino.kyle.mp_phase2.databinding.ActivityAddItemBinding
import com.mobdeve.s12.lino.kyle.mp_phase2.databinding.ActivityEditItemBinding

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

        binding!!.donebutton!!.setOnClickListener{
            val gotoViewCollectionActivity = Intent(applicationContext, ViewCollectionActivity::class.java)

            val bundle = Bundle()
            bundle.putString("name", binding!!.itemnameinput!!.toString())
            bundle.putString("price", binding!!.itempriceinput!!.toString())
            bundle.putString("description", binding!!.itemdescriptioninput!!.toString())

            startActivity(gotoViewCollectionActivity)
        }

        binding!!.profileback!!.setOnClickListener {
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity ::class.java)
            startActivity(gotoProfileActivity )
        }
    }
}