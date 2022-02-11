package com.mobdeve.s12.anigan.lino.mobdevemp.dao

class UserItem(){
    lateinit var itemName: String
    lateinit var itemPrice:String
    lateinit var itemDescription:String
    lateinit var itemOwner:String

    constructor(itemName:String,itemPrice:String,itemDescription:String,itemOwner:String) : this(){
        this.itemName = itemName
        this.itemPrice = itemPrice
        this.itemDescription = itemDescription
        this.itemOwner = itemOwner
    }
}