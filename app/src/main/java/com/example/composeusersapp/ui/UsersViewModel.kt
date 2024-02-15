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

    private val _usersList = MutableStateFlow(UserListState())
    val usersList = _usersList.asStateFlow()

    init {
        getUsers(1)
    }

    fun getUsers(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.getUsers(results, page, seed).apply {
                getSuccess()?.let { response ->
                    updateList(response)
                    seed = response.info.seed
                } ?: updateError(getError()?.message)
            }
        }
    }

    private fun updateList(response: UsersResponse) {
        _usersList.update { userState ->
            userState.copy(data = userState.data?.let {
                it.toMutableList().apply {
                    addAll(
                        response.results.map { user ->
                            getUserUI(user)
                        })
                }
            })
        }
    }

    private fun updateError(message: String?) {
        _usersList.update { userState ->
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
                picture.medium
            )
        }
    }

    private fun getUserName(name: Name) = "${name.title} ${name.first} ${name.last}"
    private fun getRegistrationDate(date: String) = date.parseDate("yyyy-MM-dd'T'HH:mm:ssZ", "dd-MM-yyyy")
}