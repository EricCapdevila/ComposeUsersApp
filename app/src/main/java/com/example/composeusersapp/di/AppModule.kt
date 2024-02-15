package com.example.composeusersapp.di

import com.example.composeusersapp.data.ApiUsersService
import com.example.composeusersapp.data.UserRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val URL = "https://randomuser.me/api/?inc=name,email,gender,registered,phone,location,picture"

    @Provides
    @Singleton
    fun providesApiUsersService(): ApiUsersService {
       return  Retrofit.Builder()
            .baseUrl(URL).addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiUsersService::class.java)
    }

    @Provides
    @Singleton
    fun providesUserDataSource(apiUsersService: ApiUsersService): UserRepository {
        return  UserRepository(apiUsersService)
    }

}