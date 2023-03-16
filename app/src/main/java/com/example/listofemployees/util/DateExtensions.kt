package com.example.listofemployees.util

import com.example.data.BuildConfig
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(): Date? =
    try {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        simpleDateFormat.parse(this)
    } catch (e: ParseException) {
        if (BuildConfig.DEBUG) e.printStackTrace()
        null
    }

fun String.toStringDate(): String {
    val defaultValue = ""
    return try {
        val date = this.toDate() ?: return defaultValue
        val simpleDateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
        simpleDateFormat.format(date)
    } catch (e: ParseException) {
        if (BuildConfig.DEBUG) e.printStackTrace()
        defaultValue
    }
}