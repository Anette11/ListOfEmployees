package com.example.listofemployees.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listofemployees.R
import com.example.listofemployees.ui.components.*

@Composable
fun MainScreen() {
    val viewModel: MainScreenViewModel = hiltViewModel()
    val selectedTabIndex = viewModel.selectedTabIndex.collectAsState()
    if (viewModel.error) {
        ErrorScreen(
            onTryAgainClick = viewModel::onTryAgainClick
        )
        return
    }
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
            selectedTabIndex = selectedTabIndex.value,
            tabNames = viewModel.tabNames,
            onTabClick = viewModel::onTabClick,
            content = {
                if (viewModel.isLoading) {
                    ListShimmer()
                    return@Tabs
                }
                ListScreen(users = viewModel.usersFiltered)
            }
        )
    }
}