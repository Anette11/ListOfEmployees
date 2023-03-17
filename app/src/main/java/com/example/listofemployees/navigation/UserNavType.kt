package com.example.listofemployees.navigation

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.example.domain.data.remote.Item
import com.google.gson.Gson

class UserNavType : NavType<Item>(isNullableAllowed = false) {

    override fun get(
        bundle: Bundle,
        key: String
    ): Item? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> bundle.getParcelable(
            Screen.Details.getUserArgs(),
            Item::class.java
        )
        else -> bundle.getParcelable(Screen.Details.getUserArgs())
    }

    override fun parseValue(
        value: String
    ): Item = Gson().fromJson(value, Item::class.java)

    override fun put(
        bundle: Bundle, key:
        String,
        value: Item
    ) = bundle.putParcelable(key, value)
}

fun Item.toJson(): String = Uri.encode(Gson().toJson(this))