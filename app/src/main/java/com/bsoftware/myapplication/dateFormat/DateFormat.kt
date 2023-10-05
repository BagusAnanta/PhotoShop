package com.bsoftware.myapplication.dateFormat

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateFormat {

    fun getDate() : String{
        val dateNow = LocalDate.now()
        val formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return dateNow.format(formatterDate)
    }
}