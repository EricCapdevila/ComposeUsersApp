package com.example.composeusersapp.ui

data class UserListState(
    val data: MutableList<UserUI>? = null,
    val error: String? = null
)

data class UserUI(
    val name: String,
    val email: String,
    val gender: String,
    val registered: String,
    val phone: String,
    val picture: String
)
