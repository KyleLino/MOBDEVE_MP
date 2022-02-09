package com.mobdeve.s12.anigan.lino.mobdevemp

class User(){

    lateinit var username: String
    lateinit var name: String
    lateinit var password: String

    var bts: Int = 0
    var exo: Int = 0
    var wannaone: Int = 0
    var got7: Int = 0
    var nct: Int = 0

    var bp: Int = 0
    var twice: Int = 0
    var rv: Int = 0
    var everglow: Int = 0
    var mamamoo: Int = 0

    var others: Int = 0



    //lateinit var exo: Int

    constructor(username : String, name : String, password : String, bts : Int, exo:Int,wannaone:Int, got7:Int, nct:Int, bp:Int, twice:Int, rv:Int, everglow:Int, mamamoo:Int, others:Int) : this(){
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
