package com.mobdeve.s12.anigan.lino.mobdevemp.dao
import com.mobdeve.s12.anigan.lino.mobdevemp.model.Item


interface UserDAO {
    fun getUsers():ArrayList<Item?>?
    fun getUser(userid: Int): Item?
    fun updateUser(user: Item?): Int
    fun deleteUser(userid: Int): Int
}