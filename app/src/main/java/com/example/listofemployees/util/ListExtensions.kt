package com.example.listofemployees.util

import com.example.domain.data.remote.Item
import java.util.*

fun List<Item>.toListScreenItems(): List<ScreenItem> {
    val listTransformed = mutableListOf<ScreenItem>()
    val calendarToday = Calendar.getInstance()
    this
        .filter { item -> item.birthday.toDate() != null }
        .forEach { item ->
            val screenItemYear = ScreenItem.Year(
                year = calendarToday.get(Calendar.YEAR).plus(1).toString()
            )
            if (!listTransformed.contains(screenItemYear)) {
                val dateBirthday = item.birthday.toDate()!!
                val calendarBirthday = Calendar.getInstance()
                calendarBirthday.time = dateBirthday
                calendarBirthday.set(Calendar.YEAR, calendarToday.get(Calendar.YEAR))
                if (calendarBirthday.before(calendarToday)) listTransformed.add(screenItemYear)
            }
            listTransformed.add(ScreenItem.User(user = item))
        }
    return listTransformed
}

fun List<Item>.toSimpleListScreenItems(): List<ScreenItem> =
    this.map { item -> ScreenItem.User(user = item) }