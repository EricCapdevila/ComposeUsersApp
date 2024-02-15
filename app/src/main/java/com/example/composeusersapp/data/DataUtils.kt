package com.example.composeusersapp.data

import java.text.SimpleDateFormat
import java.util.Locale

fun String.parseDate(fromFormat: String, toFormat: String): String{
    val sourceFormat = SimpleDateFormat(fromFormat, Locale.getDefault())
    val requiredFormat = SimpleDateFormat(toFormat, Locale.getDefault())

    return try {
        sourceFormat.parse(this)?.let{
            requiredFormat.format(it)
        } ?: "-"
    } catch (e: Exception) {
        return e.message ?: "wrong date format"
    }
}