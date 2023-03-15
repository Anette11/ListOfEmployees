package com.example.listofemployees.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listofemployees.R
import com.example.listofemployees.ui.components.*

@Composable
fun MainScreen() {
    val viewModel: MainScreenViewModel = hiltViewModel()
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
            tabIndex = viewModel.tabIndex,
            selectedTabIndex = viewModel.selectedTabIndex,
            tabNames = viewModel.tabNames,
            onTabClick = viewModel::onTabClick,
            content = {
                if (viewModel.isLoading) {
                    ListShimmer()
                    return@Tabs
                }
                ListScreen(users = viewModel.users)
            }
        )
    }
}