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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val dispatchersProvider: DispatchersProvider,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    var isLoading by mutableStateOf(true)
        private set

    var error by mutableStateOf(false)
        private set

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
                    error = true
                }
                if (networkResult is Success) {
                    error = false
                    isLoading = false
                    users = networkResult.users
                }
            }
            .catch { error = true }
            .collect()
    }

    fun onTryAgainClick() = getUsers()

    init {
        getUsers()
    }
}