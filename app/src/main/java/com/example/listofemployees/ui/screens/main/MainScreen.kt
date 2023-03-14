package com.example.listofemployees.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listofemployees.R
import com.example.listofemployees.ui.components.ListScreen
import com.example.listofemployees.ui.components.SearchField
import com.example.listofemployees.ui.components.Tabs

@Composable
fun MainScreen() {
    val viewModel: MainScreenViewModel = hiltViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ) {
        SearchField(
            value = viewModel.value,
            onValueChange = viewModel::onValueChange,
            onSearchClick = viewModel::onSearchClick,
            onMenuClick = viewModel::onMenuClick
        )
        Tabs(
            tabIndex = viewModel.tabIndex,
            selectedTabIndex = viewModel.selectedTabIndex,
            tabNames = viewModel.tabNames,
            onTabClick = viewModel::onTabClick,
            content = { ListScreen(users = viewModel.users) }
        )
    }
}