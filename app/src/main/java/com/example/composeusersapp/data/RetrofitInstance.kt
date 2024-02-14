package com.example.composeusersapp.data

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val URL = "https://randomuser.me/api/?inc=name,email,gender,registered,phone,location"

    val api: ApiUsersService by lazy {
        Retrofit.Builder()
            .baseUrl(URL).addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiUsersService::class.java)
    }
}