package com.mobdeve.s12.lino.kyle.mp_phase2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.mobdeve.s12.lino.kyle.mp_phase2.R
import com.mobdeve.s12.lino.kyle.mp_phase2.databinding.ActivityMainBinding
import com.mobdeve.s12.lino.kyle.mp_phase2.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    var binding: ActivityProfileBinding? = null
    var username: TextView? = null
    var viewcollections: TextView? = null
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
    }

    override fun onStart() {
        super.onStart()

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        username = findViewById(R.id.textusername)
        viewcollections = findViewById(R.id.viewcollections)
        tradeoffers = findViewById(R.id.tradeoffers)
        wishlist = findViewById(R.id.wishlist)
        fandoms = findViewById(R.id.fandoms)
        editprofile = findViewById(R.id.editprofile)
        logout = findViewById(R.id.logout)

        var bundle = intent.extras
        var bundleusername = bundle?.getString("username")
        binding!!.textusername.text = bundleusername

        viewcollections!!.setOnClickListener{
            Log.i(TAG,"pressed view collections")
            val gotoViewCollectionActivity = Intent(applicationContext, ViewCollectionActivity::class.java)

            var bundle = Bundle()
            bundle.putString("username", username!!.text.toString())

            gotoViewCollectionActivity.putExtras(bundle)
            startActivity(gotoViewCollectionActivity)
        }

        tradeoffers!!.setOnClickListener{
            Log.i(TAG,"pressed tradeoffers")
        }

        wishlist!!.setOnClickListener{
            Log.i(TAG,"pressed wishlist")
        }

        fandoms!!.setOnClickListener{
            Log.i(TAG,"pressed fandoms")
        }

        editprofile!!.setOnClickListener{
            Log.i(TAG,"pressed edit profile")
        }

        logout!!.setOnClickListener{
            Log.i(TAG,"pressed log out")
            val gotoMainActivity = Intent(applicationContext, MainActivity::class.java)
            startActivity(gotoMainActivity)
        }

        binding!!.browsecollection!!.setOnClickListener{
            Log.i(TAG,"pressed browse collection")
            val gotoBrowseCollectionActivity = Intent(applicationContext, BrowseCollectionActivity ::class.java)
            startActivity(gotoBrowseCollectionActivity )
        }

    }
}