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

fun String.toCurrentYear(): Date? {
    val dateBirthday = this.toDate()
    val calendarToday = Calendar.getInstance()
    val calendarBirthday = Calendar.getInstance()
    if (dateBirthday == null) return null
    calendarBirthday.time = dateBirthday
    calendarBirthday.set(Calendar.YEAR, calendarToday.get(Calendar.YEAR))
    return calendarBirthday.time
}

fun String.toBirthday(): String {
    val defaultValue = ""
    return try {
        val date = this.toDate() ?: return defaultValue
        val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        simpleDateFormat.format(date)
    } catch (e: Exception) {
        if (BuildConfig.DEBUG) e.printStackTrace()
        defaultValue
    }
}