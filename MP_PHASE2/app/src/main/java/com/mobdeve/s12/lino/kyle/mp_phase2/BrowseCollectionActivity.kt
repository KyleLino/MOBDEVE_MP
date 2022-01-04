package com.mobdeve.s12.lino.kyle.mp_phase2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s12.lino.kyle.mp_phase2.databinding.ActivityBrowseCollectionBinding
import com.mobdeve.s12.lino.kyle.mp_phase2.databinding.ActivityProfileBinding
import com.mobdeve.s12.lino.kyle.mp_phase2.databinding.ActivityViewItemBinding

class BrowseCollectionActivity : AppCompatActivity() {

    var binding: ActivityBrowseCollectionBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse_collection)
    }

    override fun onStart() {
        super.onStart()

        binding = ActivityBrowseCollectionBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.profileback!!.setOnClickListener {
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity ::class.java)
            startActivity(gotoProfileActivity )
        }
    }
}