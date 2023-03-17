package com.example.listofemployees.ui.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.domain.data.remote.Item
import com.example.listofemployees.R
import com.example.listofemployees.util.DetailsScreenItem
import com.example.listofemployees.util.ResourcesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    var screenItems by mutableStateOf(emptyList<DetailsScreenItem>())
        private set

    fun fillScreenItems(
        user: Item? = null
    ) {
        screenItems = listOf(
            DetailsScreenItem.Header(
                onBackClick = {},
                image = user?.avatarUrl ?: R.drawable.ic_placeholder,
                firstName = user?.firstName ?: resourcesProvider.getString(R.string.empty),
                lastName = user?.lastName ?: resourcesProvider.getString(R.string.empty),
                userTag = user?.userTag ?: resourcesProvider.getString(R.string.empty),
                position = user?.position ?: resourcesProvider.getString(R.string.empty)
            ),
            DetailsScreenItem.Birthday(
                birthday = user?.birthday ?: resourcesProvider.getString(R.string.empty),
                age = if (user == null) resourcesProvider.getString(R.string.empty) else "age"
            ),
            DetailsScreenItem.DividerItem,
            DetailsScreenItem.Phone(
                phone = user?.phone ?: resourcesProvider.getString(R.string.empty)
            )
        )
    }

    init {
        fillScreenItems()
    }
}