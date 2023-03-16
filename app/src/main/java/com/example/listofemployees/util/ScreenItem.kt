package com.example.listofemployees.util

import com.example.domain.data.remote.Item

sealed class ScreenItem {

    data class User(
        val user: Item
    ) : ScreenItem()

    data class Year(
        val year: String
    ) : ScreenItem()
}