package com.mobdeve.s12.anigan.lino.mobdevemp.dao

class UserWishList() {
    lateinit var itemName: String
    lateinit var itemOwner:String

    constructor(itemName:String,itemOwner:String) : this(){
        this.itemName = itemName
        this.itemOwner = itemOwner
    }
}