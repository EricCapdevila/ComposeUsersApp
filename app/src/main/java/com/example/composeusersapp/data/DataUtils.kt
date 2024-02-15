package com.example.composeusersapp.data

import java.text.SimpleDateFormat
import java.util.Locale

class DataUtils {


    companion object DataFormater {

        fun parse

    }
}

fun String.parseDate(fromFormat: String, toFormat: String): String{
    val sourceFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
    val requiredFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    return sourceFormat.parse(this)?.let{
        requiredFormat.format(it)
    } ?: "-"
}