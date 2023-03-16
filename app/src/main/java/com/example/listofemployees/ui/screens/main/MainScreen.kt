package com.example.listofemployees.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listofemployees.R
import com.example.listofemployees.ui.components.*
import com.example.listofemployees.util.SortType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen() {
    val viewModel: MainScreenViewModel = hiltViewModel()
    val selectedTabIndex = viewModel.selectedTabIndex.collectAsState()
    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
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
    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetContent = {
            SortBottomSheet(
                isSortAlphabeticallySelected = viewModel.sortType == SortType.ALPHABETICALLY,
                isSortByBirthdaySelected = viewModel.sortType == SortType.BY_BIRTHDAY,
                onSortAlphabeticallyClick = {
                    viewModel.onSortTypeSelected(sortType = SortType.ALPHABETICALLY)
                    hideWithDelay(
                        scope = scope,
                        state = modalBottomSheetState
                    )
                },
                onSortByBirthdayClick = {
                    viewModel.onSortTypeSelected(sortType = SortType.BY_BIRTHDAY)
                    hideWithDelay(
                        scope = scope,
                        state = modalBottomSheetState
                    )
                }
            )
        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen._20dp),
            topEnd = dimensionResource(id = R.dimen._20dp)
        ),
        sheetBackgroundColor = Color.Transparent,
        content = {
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
                        onMenuClick = { scope.launch { modalBottomSheetState.show() } },
                        isMenuSelected = viewModel.sortType != SortType.ALPHABETICALLY
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
                            ListScreen(users = viewModel.usersFiltered.value)
                        }
                    )
                }
                PullRefreshIndicator(
                    refreshing = viewModel.isRefreshing,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                    contentColor = colorResource(id = R.color.purple)
                )
                viewModel.snackBarInfo?.let { snackBarInfo ->
                    SnackBarInfo(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        color = snackBarInfo.color,
                        text = snackBarInfo.text
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
private fun hideWithDelay(
    scope: CoroutineScope,
    state: ModalBottomSheetState
) = scope.launch {
    delay(250L)
    state.hide()
}