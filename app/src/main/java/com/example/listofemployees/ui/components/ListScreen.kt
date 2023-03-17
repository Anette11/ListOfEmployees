package com.example.listofemployees.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.domain.data.remote.Item
import com.example.listofemployees.util.ScreenItem

@Composable
fun ListScreen(
    users: List<ScreenItem>,
    showBirthday: Boolean,
    onUserClick: (Item) -> Unit
) = LazyColumn(
    modifier = Modifier.fillMaxSize()
) {
    items(count = users.size) { index ->
        when (val screenItem = users[index]) {
            is ScreenItem.User -> OneItem(
                showBirthday = showBirthday,
                user = screenItem.user,
                onUserClick = onUserClick
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
        showBirthday = true,
        onUserClick = {}
    )