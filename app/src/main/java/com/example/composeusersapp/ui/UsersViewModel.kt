package com.example.composeusersapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeusersapp.data.UserRepository
import com.example.composeusersapp.data.models.GenericError
import com.example.composeusersapp.data.models.ServiceResult
import com.example.composeusersapp.data.models.User
import com.example.composeusersapp.data.models.UsersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val dataSource: UserRepository
) : ViewModel() {

    val results = 10

    val usersResult = MutableLiveData<ServiceResult<UsersResponse, GenericError>>()
    val users = mutableListOf<User>()
    var seed = ""

    fun getUsers(page: Int) {
        viewModelScope.launch {
            val result = dataSource.getUsers(results, page, seed).apply {
                getSuccess()?.let{
                    users.addAll(it.results)
                    seed = it.info.seed
                }
            }
            usersResult.value = result
        }
    }
}