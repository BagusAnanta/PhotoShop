package com.bsoftware.myapplication.generateID

import kotlin.random.Random


class GenerateIDProduct {

    fun generateIDNumber(length : Int) : String{
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val random = Random.Default
        return (1..length)
            .map { charset[random.nextInt(charset.length)] }
            .joinToString("")
    }
}