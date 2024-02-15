package com.example.composeusersapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeusersapp.data.UserRepository
import com.example.composeusersapp.data.models.Name
import com.example.composeusersapp.data.models.User
import com.example.composeusersapp.data.parseDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val dataSource: UserRepository
) : ViewModel() {

    val results = 10

    private val _usersList = MutableStateFlow(mutableListOf<UserUI>())
    val usersList = _usersList.asStateFlow()

    var seed = ""

    data class UserUI(
        val name: String,
        val email: String,
        val gender: String,
        val registered: String,
        val phone: String,
        val picture: String
    )

    fun getUsers(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.getUsers(results, page, seed).apply {
                getSuccess()?.let {
                    usersList.value.addAll(
                        it.results.map { user -> getUserUI(user) }
                    )
                    seed = it.info.seed
                }
                // HANDLE ERROR
            }
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