package com.example.listofemployees.ui.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.listofemployees.R
import com.example.listofemployees.util.DetailsScreenItem
import com.example.listofemployees.util.ResourcesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    resourcesProvider: ResourcesProvider
) : ViewModel() {

    private val defaultScreensList = listOf(
        DetailsScreenItem.Header(
            onBackClick = {},
            image = R.drawable.ic_placeholder,
            firstName = resourcesProvider.getString(R.string.empty),
            lastName = resourcesProvider.getString(R.string.empty),
            userTag = resourcesProvider.getString(R.string.empty),
            position = resourcesProvider.getString(R.string.empty)
        ),
        DetailsScreenItem.Birthday(
            birthday = resourcesProvider.getString(R.string.empty),
            age = resourcesProvider.getString(R.string.empty)
        ),
        DetailsScreenItem.DividerItem,
        DetailsScreenItem.Phone(
            phone = resourcesProvider.getString(R.string.empty)
        )
    )

    var screenItems by mutableStateOf(defaultScreensList)
        private set
}