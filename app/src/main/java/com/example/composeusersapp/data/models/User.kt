package com.example.composeusersapp.data.models

data class User(
    val name: Name,
    val email: String,
    val gender: String,
    val registered: Registered,
    val phone: String,
    val picture: Picture
)
