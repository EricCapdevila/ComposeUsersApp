package com.example.composeusersapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeusersapp.data.UserRepository
import com.example.composeusersapp.data.models.Name
import com.example.composeusersapp.data.models.User
import com.example.composeusersapp.data.models.UsersResponse
import com.example.composeusersapp.data.parseDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val dataSource: UserRepository
) : ViewModel() {

    private val results = 10
    private var seed = ""

    private val _usersState = MutableStateFlow(UserListState())
    val usersState = _usersState.asStateFlow()

    init {
        getUsers(1)
    }

    fun getUsers(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.getUsers(results, page, seed).apply {
                getSuccess()?.let { response ->
                    updateList(response.results)
                    seed = response.info.seed
                } ?: updateError(getError()?.message)
            }
        }
    }

    fun filterUsers(input: String) {
        viewModelScope.launch {
            val filteredData = if (input.isEmpty()) {
                null
            } else {
                _usersState.value.data.filter {
                    it.name.contains(input) || it.email.contains(input)
                }
            }
            _usersState.update { userState ->
                userState.copy(filteredData = filteredData)
            }
        }
    }

    private fun updateList(users: List<User>) {
        _usersState.update { userState ->
            userState.copy(error = null, data = userState.data.toMutableList().apply {
                addAll(
                    users.map { user ->
                        getUserUI(user)
                    })
            }
            )
        }
    }

    private fun updateError(message: String?) {
        _usersState.update { userState ->
            userState.copy(error = message?.let { message } ?: userState.error)
        }
    }

    // GET THE USER DATA UI READY

    fun getUserUI(user: User): UserUI {
        user.apply {
            return UserUI(
                getUserName(name),
                email,
                gender,
                getRegistrationDate(registered.date),
                phone,
                picture.large
            )
        }
    }

    private fun getUserName(name: Name) = "${name.title} ${name.first} ${name.last}"
    private fun getRegistrationDate(date: String) = date.parseDate("yyyy-MM-dd'T'HH:mm:ssZ", "dd-MM-yyyy")
}