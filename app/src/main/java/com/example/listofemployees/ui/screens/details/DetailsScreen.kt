package com.example.listofemployees.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.data.remote.Item
import com.example.listofemployees.R
import com.example.listofemployees.ui.components.BirthdayItem
import com.example.listofemployees.ui.components.DividerItem
import com.example.listofemployees.ui.components.HeaderItem
import com.example.listofemployees.ui.components.PhoneItem
import com.example.listofemployees.util.DetailsScreenItem

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    user: Item,
    onNavigateBack: () -> Unit,
    onPhoneClick: (String) -> Unit
) {
    LaunchedEffect(key1 = user) {
        viewModel.fillScreenItems(user = user)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ) {
        items(viewModel.screenItems) { screenItem ->
            when (screenItem) {
                is DetailsScreenItem.Header -> HeaderItem(
                    onBackClick = onNavigateBack,
                    image = screenItem.image,
                    firstName = screenItem.firstName,
                    lastName = screenItem.lastName,
                    userTag = screenItem.userTag,
                    position = screenItem.position
                )
                is DetailsScreenItem.Birthday -> BirthdayItem(
                    birthday = screenItem.birthday,
                    age = screenItem.age
                )
                DetailsScreenItem.DividerItem -> DividerItem()
                is DetailsScreenItem.Phone -> PhoneItem(
                    phone = screenItem.phone,
                    onPhoneClick = onPhoneClick
                )
            }
        }
    }
}