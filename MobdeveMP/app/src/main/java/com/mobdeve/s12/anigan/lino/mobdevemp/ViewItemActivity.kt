package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityViewItemBinding

class ViewItemActivity : AppCompatActivity() {

    var binding: ActivityViewItemBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewItemBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val bundle = intent.extras
        val name = bundle!!.getString("itemname")
        val price = bundle!!.getString("itemprice")
        val description = bundle!!.getString("itemdescription")
        var bundleusername = bundle!!.getString("itemowner")


        binding!!.itemname.text = "Name: $name"
        binding!!.itemprice.text = "Price: $price"
        binding!!.itemdescription.text = "Description: $description"

        val buttonEdit = binding!!.editbutton
        val buttonDelete = binding!!.deletebutton

        buttonEdit.setOnClickListener{
            val gotoEditItemActivity = Intent(applicationContext, EditItemActivity::class.java)
            var bundle = Bundle()
            bundle.putString("itemowner", bundleusername)
            bundle.putString("itemname", name)
            bundle.putString("itemprice", price)
            bundle.putString("itemdescription", description)
            gotoEditItemActivity.putExtras(bundle)
            startActivity(gotoEditItemActivity)
        }

        buttonDelete.setOnClickListener{

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