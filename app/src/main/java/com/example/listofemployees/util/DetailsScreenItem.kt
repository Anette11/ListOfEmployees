package com.example.listofemployees.util

sealed class DetailsScreenItem {

    data class Header(
        val image: Any?,
        val firstName: String,
        val lastName: String,
        val userTag: String,
        val position: String
    ) : DetailsScreenItem()

    data class Birthday(
        val birthday: String,
        val age: String
    ) : DetailsScreenItem()

    object DividerItem
        : DetailsScreenItem()

    data class Phone(
        val phone: String
    ) : DetailsScreenItem()
}