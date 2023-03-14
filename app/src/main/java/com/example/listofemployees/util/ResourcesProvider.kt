package com.example.listofemployees.util

import androidx.annotation.StringRes

interface ResourcesProvider {

    fun getString(@StringRes resId: Int): String
}