package com.mobdeve.s12.anigan.lino.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.UserDAO
import com.mobdeve.s12.anigan.lino.mobdevemp.dao.UserDaoArrayList
import com.mobdeve.s12.anigan.lino.mobdevemp.databinding.ActivityViewCollectionBinding
import com.mobdeve.s12.anigan.lino.mobdevemp.model.Item

class ViewCollectionActivity : AppCompatActivity() {
    var binding: ActivityViewCollectionBinding? = null
    var userList: ArrayList<Item?> = ArrayList<Item?>()
    var userDAO: UserDAO = UserDaoArrayList()
    var Adapter: Adapter? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityViewCollectionBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        populateList()
        Adapter = Adapter(applicationContext, userList!!) {index -> removeItem(index)}

        binding!!.gamesavelist.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL,
            false)

        binding!!.gamesavelist.adapter = Adapter

        binding!!.additem!!.setOnClickListener {
            val gotoAddItemActivity = Intent(applicationContext, AddItemActivity::class.java)

            startActivity(gotoAddItemActivity)
        }

        binding!!.profileback!!.setOnClickListener {
            val gotoProfileActivity = Intent(applicationContext, ProfileActivity ::class.java)
            startActivity(gotoProfileActivity )
        }



    }


    fun populateList(){
        userList = userDAO.getUsers()!!
    }

    private fun removeItem(position: Int) {
        userList.removeAt(position)
        Adapter?.setItem(userList)
    }
}