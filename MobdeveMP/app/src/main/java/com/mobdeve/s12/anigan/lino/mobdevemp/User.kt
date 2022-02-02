package com.mobdeve.s12.anigan.lino.mobdevemp

class User(){

    lateinit var username: String
    lateinit var name: String
    lateinit var password: String

    constructor(username : String, name : String, password : String) : this(){
        this.username = username
        this.name = name
        this.password = password
    }
}
