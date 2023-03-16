package com.example.listofemployees.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.domain.data.remote.Item

@Composable
fun ListScreen(
    users: List<Item>,
    showBirthday: Boolean
) = LazyColumn(
    modifier = Modifier.fillMaxSize()
) {
    items(count = users.size) { index ->
        val user = users[index]
        OneItem(
            image = user.avatarUrl,
            name = "${user.firstName} ${user.lastName}",
            userTag = user.userTag,
            position = user.position,
            birthday = user.birthday,
            showBirthday = showBirthday
        )
    }
}

@Composable
@Preview
fun ListScreenPreview() =
    ListScreen(
        users = emptyList(),
        showBirthday = true
    )