package com.mobdeve.s12.anigan.lino.mobdevemp.model

class Item(){
    lateinit var textname:String
    lateinit var textprice:String
    lateinit var textdescription:String

    constructor(textname:String,textprice:String,textdescription:String) : this(){
        this.textname = textname
        this.textprice = textprice
        this.textdescription = textdescription
    }
}


