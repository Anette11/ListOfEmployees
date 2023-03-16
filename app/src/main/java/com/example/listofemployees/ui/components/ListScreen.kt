package com.example.listofemployees.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.listofemployees.util.ScreenItem

@Composable
fun ListScreen(
    users: List<ScreenItem>,
    showBirthday: Boolean
) = LazyColumn(
    modifier = Modifier.fillMaxSize()
) {
    items(count = users.size) { index ->
        when (val screenItem = users[index]) {
            is ScreenItem.User -> OneItem(
                image = screenItem.user.avatarUrl,
                name = "${screenItem.user.firstName} ${screenItem.user.lastName}",
                userTag = screenItem.user.userTag,
                position = screenItem.user.position,
                birthday = screenItem.user.birthday,
                showBirthday = showBirthday
            )
            is ScreenItem.Year -> ItemYear(year = screenItem.year)
        }
    }
}

@Composable
@Preview
fun ListScreenPreview() =
    ListScreen(
        users = emptyList(),
        showBirthday = true
    )