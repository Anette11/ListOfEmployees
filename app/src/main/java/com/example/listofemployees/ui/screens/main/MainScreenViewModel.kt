package com.example.listofemployees.ui.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.DispatchersProvider
import com.example.domain.data.remote.Item
import com.example.domain.use_cases.GetUsersUseCase
import com.example.domain.util.NetworkFailureType
import com.example.domain.util.NetworkResult
import com.example.listofemployees.R
import com.example.listofemployees.util.*
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

    var selectedTabIndex by mutableStateOf(0)
        private set

    var isLoading by mutableStateOf(true)
        private set

    var isRefreshing by mutableStateOf(false)
        private set

    var error by mutableStateOf(false)
        private set

    var value by mutableStateOf(resourcesProvider.getString(R.string.empty))
        private set

    var snackBarInfo by mutableStateOf<SnackBarInfo?>(null)
        private set

    var sortType by mutableStateOf(SortType.ALPHABETICALLY)
        private set

    private var users by mutableStateOf<List<Item>>(emptyList())

    var usersFiltered = mutableStateOf<List<Item>>(mutableListOf())
        get() {
            return field
                .apply {
                    val newUsersFiltered = users.filter { user ->
                        when (selectedTabIndex) {
                            0 -> true
                            else -> user.department == TabType.values()[selectedTabIndex].department
                        }
                    }
                    this.value = newUsersFiltered
                }
                .apply {
                    when (sortType) {
                        SortType.ALPHABETICALLY -> (this.value as MutableList<Item>)
                            .sortBy { user -> user.firstName }
                        SortType.BY_BIRTHDAY -> (this.value as MutableList<Item>)
                            .sortedWith(nullsLast(compareBy { user -> user.birthday.toDate() }))
                    }
                }
        }
        private set

    fun onValueChange(newValue: String) {
        value = newValue
    }

    fun onSortTypeSelected(sortType: SortType) {
        this.sortType = sortType
    }

    val tabNames = buildList {
        TabType.values().forEach { tabType ->
            add(tabType.tabName)
        }
    }

    fun onTabClick(tab: Int) {
        selectedTabIndex = tab
    }

    fun onSearchClick() {

    }

    private fun getUsers() = viewModelScope.launch(dispatchersProvider.main) {
        getUsersUseCase.invoke()
            .flowOn(dispatchersProvider.io)
            .onEach { networkResult ->
                when (networkResult) {
                    is NetworkResult.Failure -> {
                        isLoading = false
                        if (!isRefreshing) error = true
                        isRefreshing = false
                        snackBarInfo = createFailureSnackBarInfo(
                            networkFailureType = networkResult.networkFailureType
                        )
                    }
                    NetworkResult.Loading -> {
                        error = false
                        isLoading = true
                    }
                    is NetworkResult.Success -> {
                        error = false
                        isLoading = false
                        users = networkResult.users
                        if (usersFiltered.value.isEmpty()) usersFiltered.value = networkResult.users
                        isRefreshing = false
                        snackBarInfo = null
                    }
                }
            }
            .catch { error = true }
            .collect()
    }

    private fun createFailureSnackBarInfo(
        networkFailureType: NetworkFailureType
    ): SnackBarInfo = when (networkFailureType) {
        NetworkFailureType.GenericError -> SnackBarInfo(
            color = R.color.red,
            text = resourcesProvider.getString(R.string.some_superintelligence_broke_everything)
        )
        NetworkFailureType.NetworkConnectionError -> SnackBarInfo(
            color = R.color.red,
            text = resourcesProvider.getString(R.string.error_internet_connection)
        )
    }

    fun onTryAgainClick() = getUsers()

    fun onRefresh() {
        snackBarInfo = null
        isRefreshing = true
        snackBarInfo = SnackBarInfo(
            color = R.color.purple,
            text = resourcesProvider.getString(R.string.refreshing_info)
        )
        getUsers()
    }

    init {
        getUsers()
    }
}