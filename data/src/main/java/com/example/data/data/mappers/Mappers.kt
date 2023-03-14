package com.example.data.data.mappers

import com.example.data.data.remote.ItemDto
import com.example.domain.data.remote.Item

fun ItemDto.toItem() =
    Item(
        avatarUrl = this.avatarUrl,
        birthday = this.birthday,
        department = this.department,
        firstName = this.firstName,
        id = this.id,
        lastName = this.lastName,
        phone = this.phone,
        position = this.position,
        userTag = this.userTag
    )