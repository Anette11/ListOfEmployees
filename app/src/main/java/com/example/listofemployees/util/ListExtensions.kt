package com.example.listofemployees.util

import com.example.domain.data.remote.Item
import java.util.*

fun List<Item>.toListScreenItems(): List<ScreenItem> {
    val listTransformed = mutableListOf<ScreenItem>()
    val listBirthdaysNextYear = mutableListOf<ScreenItem>()
    val calendarToday = Calendar.getInstance()
    this
        .filter { item -> item.birthday.toDate() != null }
        .forEach { item ->
            val screenItemYear = ScreenItem.Year(
                year = calendarToday.get(Calendar.YEAR).plus(1).toString()
            )
            val calendarBirthday = Calendar.getInstance()
            val dateBirthday = item.birthday.toDate()!!
            calendarBirthday.time = dateBirthday
            calendarBirthday.set(Calendar.YEAR, calendarToday.get(Calendar.YEAR))
            if (!listBirthdaysNextYear.contains(screenItemYear)) {
                if (calendarBirthday.before(calendarToday)) listBirthdaysNextYear.add(screenItemYear)
            }
            val screenItemUser = ScreenItem.User(user = item)
            when (calendarBirthday.before(calendarToday)) {
                true -> listBirthdaysNextYear.add(screenItemUser)
                false -> listTransformed.add(screenItemUser)
            }
        }
    return listTransformed.plus(listBirthdaysNextYear)
}

fun List<Item>.toSimpleListScreenItems(): List<ScreenItem> =
    this.map { item -> ScreenItem.User(user = item) }