package com.mobdeve.s12.anigan.lino.mobdevemp.dao

class User(){

    lateinit var username: String
    lateinit var name: String
    lateinit var password: String

    var bts: Boolean = false
    var exo: Boolean = false
    var wannaone: Boolean = false
    var got7: Boolean = false
    var nct: Boolean = false

    var bp: Boolean = false
    var twice: Boolean = false
    var rv: Boolean = false
    var everglow: Boolean = false
    var mamamoo: Boolean = false

    var others: Boolean = false



    //lateinit var exo: Int

    constructor(username : String, name : String, password : String, bts : Boolean, exo:Boolean,wannaone:Boolean, got7:Boolean, nct:Boolean, bp:Boolean, twice:Boolean, rv:Boolean, everglow:Boolean, mamamoo:Boolean, others:Boolean) : this(){
        this.username = username
        this.name = name
        this.password = password

        this.bts = bts
        this.exo = exo
        this.wannaone = wannaone
        this.nct = nct
        this.bp = bp
        this.twice = twice
        this.rv = rv
        this.everglow = everglow
        this.mamamoo = mamamoo
        this.got7 = got7

        this.others = others
    }
}
