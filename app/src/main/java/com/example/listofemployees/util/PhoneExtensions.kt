package com.example.listofemployees.util

fun String.toPhone(): String {
    val defaultValue = ""
    val phoneTrimmed = this.replace("-", "").trim()
    if (phoneTrimmed.length != 10) return defaultValue
    val regex = """(\d)(\d{3})(\d{3})(\d{2})(\d{2})""".toRegex()
    val phoneNumber = "7".plus(phoneTrimmed)
    return regex.replace(phoneNumber, "+$1 ($2) $3 $4 $5")
}