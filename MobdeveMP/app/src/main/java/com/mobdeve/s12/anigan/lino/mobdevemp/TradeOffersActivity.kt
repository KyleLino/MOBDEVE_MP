package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityFandomBinding
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityProfileBinding
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityTradeOffersBinding

class TradeOffersActivity : AppCompatActivity() {

    lateinit var binding: ActivityTradeOffersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trade_offers)

        var bundle = intent.extras
        val bundleusername = bundle!!.getString("username")

        binding = ActivityTradeOffersBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        binding!!.profileback!!.setOnClickListener {
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity ::class.java)
            var bundle = Bundle()
            bundle.putString("username", bundleusername)
            gotoProfileActivity.putExtras(bundle)
            startActivity(gotoProfileActivity )
        }
    }
}