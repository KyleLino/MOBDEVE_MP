package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityAddWishListBinding
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityMainBinding
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityWishlistBinding

class WishlistActivity : AppCompatActivity() {

    lateinit var binding: ActivityWishlistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)

        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var bundle = intent.extras
        var bundleusername = bundle!!.getString("username")

        binding.addwishlist.setOnClickListener {
            val gotoAddWishListActivity = Intent(applicationContext, AddWishListActivity::class.java)
            var bundle = Bundle()
            bundle.putString("username", bundleusername)
            gotoAddWishListActivity.putExtras(bundle)
            startActivity(gotoAddWishListActivity)
        }
    }


}