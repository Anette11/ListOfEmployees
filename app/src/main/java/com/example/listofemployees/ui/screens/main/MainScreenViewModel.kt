package com.example.listofemployees.ui.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.DispatchersProvider
import com.example.domain.data.remote.Item
import com.example.domain.use_cases.GetUsersUseCase
import com.example.listofemployees.R
import com.example.listofemployees.util.ResourcesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val dispatchersProvider: DispatchersProvider,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    val tabIndex by mutableStateOf(0)
    val selectedTabIndex by mutableStateOf(0)

    var value by mutableStateOf(resourcesProvider.getString(R.string.empty))
        private set

    fun onValueChange(newValue: String) {
        value = newValue
    }

    fun onSearchClick() {

    }

    fun onMenuClick() {

    }

    val tabNames = listOf(
        resourcesProvider.getString(R.string.all),
        resourcesProvider.getString(R.string.designers),
        resourcesProvider.getString(R.string.analysts),
        resourcesProvider.getString(R.string.managers),
        resourcesProvider.getString(R.string.ios)
    )

    fun onTabClick(tab: Int) {

    }

    var users by mutableStateOf<List<Item>>(emptyList())
        private set

    private fun getUsers() = viewModelScope.launch(dispatchersProvider.io) {
        val users = getUsersUseCase.invoke()
        this@MainScreenViewModel.users = users
    }

    init {
        getUsers()
    }
}