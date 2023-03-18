package com.example.listofemployees.util

import com.example.data.BuildConfig
import org.joda.time.LocalDate
import org.joda.time.Years
import java.text.ChoiceFormat
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

fun String.toAge(): String {
    val defaultValue = ""
    val date = this.toDate() ?: return defaultValue
    val localDateBirth = LocalDate(date.time)
    val localDateNow = LocalDate()
    val years = Years.yearsBetween(localDateBirth, localDateNow)
    val age = years.years
    if (age < 0) return defaultValue
    val limits = doubleArrayOf(0.0, 1.0, 2.0, 5.0)
    val formats = arrayOf("лет", "год", "года", "лет")
    val choiceFormat = ChoiceFormat(limits, formats)
    val rule = if (age % 100 in 11..14) age else age % 10
    return "$age ${choiceFormat.format(rule)}"
}