package com.example.listofemployees.ui.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.DispatchersProvider
import com.example.domain.data.remote.Item
import com.example.domain.use_cases.GetUsersUseCase
import com.example.domain.util.Failure
import com.example.domain.util.Loading
import com.example.domain.util.Success
import com.example.listofemployees.R
import com.example.listofemployees.util.ResourcesProvider
import com.example.listofemployees.util.TabType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val dispatchersProvider: DispatchersProvider,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    private val _selectedTabIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val selectedTabIndex: StateFlow<Int> = _selectedTabIndex.asStateFlow()

    var isLoading by mutableStateOf(true)
        private set

    var isRefreshing by mutableStateOf(false)
        private set

    var error by mutableStateOf(false)
        private set

    var value by mutableStateOf(resourcesProvider.getString(R.string.empty))
        private set

    private var users by mutableStateOf<List<Item>>(emptyList())

    var usersFiltered by mutableStateOf<List<Item>>(emptyList())
        private set

    fun onValueChange(newValue: String) {
        value = newValue
    }

    val tabNames = buildList {
        TabType.values().forEach { tabType ->
            add(tabType.tabName)
        }
    }

    fun onTabClick(tab: Int) {
        _selectedTabIndex.value = tab
    }

    fun onSearchClick() {

    }

    fun onMenuClick() {

    }

    private fun getUsers() = viewModelScope.launch(dispatchersProvider.main) {
        getUsersUseCase.invoke(defaultErrorMessage = resourcesProvider.getString(R.string.error_occurred))
            .flowOn(dispatchersProvider.io)
            .onEach { networkResult ->
                if (networkResult is Loading) {
                    error = false
                    isLoading = true
                }
                if (networkResult is Failure) {
                    isLoading = false
                    if (!isRefreshing) error = true
                    isRefreshing = false
                }
                if (networkResult is Success) {
                    error = false
                    isLoading = false
                    users = networkResult.users
                    if (usersFiltered.isEmpty()) usersFiltered = networkResult.users
                    isRefreshing = false
                }
            }
            .catch { error = true }
            .collect()
    }

    fun onTryAgainClick() = getUsers()

    fun onRefresh() {
        isRefreshing = true
        getUsers()
    }

    private fun applyFilter() = viewModelScope.launch {
        selectedTabIndex.collect { index ->
            val newUsersFiltered = users.filter { user ->
                when (index) {
                    0 -> true
                    else -> user.department == TabType.values()[index].department
                }
            }
            usersFiltered = newUsersFiltered
        }
    }

    init {
        getUsers()
        applyFilter()
    }
}