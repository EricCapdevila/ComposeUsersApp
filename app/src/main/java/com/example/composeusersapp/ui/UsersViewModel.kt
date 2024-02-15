package com.example.composeusersapp.ui

import androidx.compose.foundation.layout.FlowColumn
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeusersapp.data.UserRepository
import com.example.composeusersapp.data.models.GenericError
import com.example.composeusersapp.data.models.ServiceResult
import com.example.composeusersapp.data.models.User
import com.example.composeusersapp.data.models.UsersResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val dataSource: UserRepository
) : ViewModel() {

    val results = 10


    val usersList = MutableStateFlow(mutableListOf<User>())
    val usersResult = MutableLiveData<ServiceResult<UsersResponse, GenericError>>()
   // val users = mutableListOf<User>()
    var seed = ""

    fun getUsers(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = dataSource.getUsers(results, page, seed).apply {
                getSuccess()?.let{
                    usersList.value.addAll(it.results)

                    //users.addAll(it.results)
                    seed = it.info.seed
                }
            }
            usersResult.value = result
        }
    }
}