package com.example.listofemployees.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listofemployees.R
import com.example.listofemployees.ui.components.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen() {
    val viewModel: MainScreenViewModel = hiltViewModel()
    val selectedTabIndex = viewModel.selectedTabIndex.collectAsState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = viewModel.isRefreshing,
        onRefresh = viewModel::onRefresh
    )
    if (viewModel.error) {
        ErrorScreen(
            onTryAgainClick = viewModel::onTryAgainClick
        )
        return
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(state = pullRefreshState)
    ) {
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
                    if (viewModel.isLoading && !viewModel.isRefreshing) {
                        ListShimmer()
                        return@Tabs
                    }
                    ListScreen(users = viewModel.usersFiltered)
                }
            )
        }
        PullRefreshIndicator(
            refreshing = viewModel.isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            contentColor = colorResource(id = R.color.purple)
        )
    }
}