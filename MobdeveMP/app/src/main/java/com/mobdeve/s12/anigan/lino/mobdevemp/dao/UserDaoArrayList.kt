package com.mobdeve.s12.anigan.lino.mobdevemp.dao

import com.mobdeve.s12.anigan.lino.mobdevemp.model.Item

class UserDaoArrayList: UserDAO {

    var userList = ArrayList<Item?>()

    init {
        userList.add(Item("BTS PC", "500", "2018 bts merch"))
        userList.add(Item("BP PC", "400", "blackpink is the revolution"))
        userList.add(Item("RV PC", "300", "red velvet forever"))
        userList.add(Item("TXT PC", "200", "luv it"))
        userList.add(Item("EXO PC", "100", "so cool"))

    }

    override fun getUsers(): ArrayList<Item?>? = userList

    override fun getUser(userid: Int): Item? {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: Item?): Int {
        TODO("Not yet implemented")
    }

    override fun deleteUser(userid: Int): Int {
        TODO("Not yet implemented")
    }

}