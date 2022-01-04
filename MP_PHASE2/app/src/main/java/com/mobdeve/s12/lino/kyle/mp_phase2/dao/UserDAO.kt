package com.mobdeve.s12.lino.kyle.mp_phase2.dao
import com.mobdeve.s12.lino.kyle.mp_phase2.model.Item

interface UserDAO {
    fun getUsers():ArrayList<Item?>?
    fun getUser(userid: Int): Item?
    fun updateUser(user: Item?): Int
    fun deleteUser(userid: Int): Int
}