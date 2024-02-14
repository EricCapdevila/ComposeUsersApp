package com.example.composeusersapp.data

import com.example.composeusersapp.data.models.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiUsersService {
    @GET("/api")
    suspend fun getUsers( @Query("results") results: Int, @Query("page") page: Int, @Query("seed") seed: String): UsersResponse
}