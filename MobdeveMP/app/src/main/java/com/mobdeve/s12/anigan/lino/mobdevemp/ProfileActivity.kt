package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    var binding: ActivityProfileBinding? = null
    var username: TextView? = null
    var viewcollections: TextView? = null
    var usercollections: TextView? = null
    var tradeoffers: TextView? = null
    var wishlist: TextView? = null
    var fandoms: TextView? = null
    var editprofile: TextView? = null
    var logout: TextView? = null
    var profileimage: ImageView? = null

    var TAG = "PROFILEACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        checkLogin()
    }

    override fun onStart() {
        super.onStart()

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        username = findViewById(R.id.textusername)
        viewcollections = findViewById(R.id.viewcollections)
        usercollections = findViewById(R.id.usercollections)
        tradeoffers = findViewById(R.id.tradeoffers)
        wishlist = findViewById(R.id.wishlist)
        fandoms = findViewById(R.id.fandoms)
        editprofile = findViewById(R.id.editprofile)
        logout = findViewById(R.id.logout)

        var bundle = intent.extras
        var bundleusername = bundle?.getString("username")
        //var bundlename = bundle?.getString("name")
        //var bundlepassword = bundle?.getString("password")

        binding!!.textusername.text = bundleusername

        binding!!.qrscanner.setOnClickListener {
            val gotoQrActivity = Intent(applicationContext, QrActivity::class.java)
            startActivity(gotoQrActivity)
        }


        viewcollections!!.setOnClickListener {
            Log.i(TAG, "pressed view collections")
            val gotoViewCollectionActivity =
                Intent(applicationContext, ViewCollectionActivity::class.java)

            var bundle = Bundle()
            bundle.putString("username", username!!.text.toString())

            gotoViewCollectionActivity.putExtras(bundle)
            startActivity(gotoViewCollectionActivity)
        }

        usercollections!!.setOnClickListener {
            Log.i(TAG, "pressed user collections")
            val gotoUserCollection = Intent(applicationContext, BrowseCollectionActivity::class.java)

            var bundle = Bundle()
            bundle.putString("username", username!!.text.toString())

            gotoUserCollection.putExtras(bundle)
            startActivity(gotoUserCollection)
        }

        tradeoffers!!.setOnClickListener {
            Log.i(TAG, "pressed tradeoffers")
            val gotoTradeOffersActivity =
                Intent(applicationContext, TradeOffersActivity::class.java)

            var bundle = Bundle()
            bundle.putString("username", username!!.text.toString())

            gotoTradeOffersActivity.putExtras(bundle)
            startActivity(gotoTradeOffersActivity)
        }

        wishlist!!.setOnClickListener {
            Log.i(TAG, "pressed wishlist")

            val gotoWishlistActivity = Intent(applicationContext, WishlistActivity::class.java)
            var bundle = Bundle()
            bundle.putString("username", username!!.text.toString())

            gotoWishlistActivity.putExtras(bundle)
            startActivity(gotoWishlistActivity)
        }

        fandoms!!.setOnClickListener {
            Log.i(TAG, "pressed fandoms")

            val gotoFandomActivity = Intent(applicationContext, FandomActivity::class.java)
            var bundle = Bundle()
            bundle.putString("username", bundleusername)
            //bundle.putString("name", bundlename)
            //bundle.putString("password", bundlepassword)
            gotoFandomActivity.putExtras(bundle)
            startActivity(gotoFandomActivity)
        }

        editprofile!!.setOnClickListener {
            Log.i(TAG, "pressed edit profile")
            val gotoEditProfileActivity =
                Intent(applicationContext, EditProfileActivity::class.java)
            var bundle = Bundle()
            bundle.putString("username", bundleusername)
            //bundle.putString("name", bundlename)
            //bundle.putString("password", bundlepassword)
            gotoEditProfileActivity.putExtras(bundle)
            startActivity(gotoEditProfileActivity)
        }

        logout!!.setOnClickListener {
            Log.i(TAG, "pressed log out")
            val gotoMainActivity = Intent(applicationContext, MainActivity::class.java)
            startActivity(gotoMainActivity)
        }
    }

    private fun checkLogin() {
        var bundle = intent.extras
        var bundleusername = bundle?.getString("username")
        val goToMainActivity = Intent(applicationContext, MainActivity::class.java)

        if (bundleusername == null) {
            startActivity(goToMainActivity)
        }
    }
}

    