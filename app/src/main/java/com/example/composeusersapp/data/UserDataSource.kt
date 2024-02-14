package com.example.composeusersapp.data

import com.example.composeusersapp.data.models.GenericError
import com.example.composeusersapp.data.models.ServiceResult
import com.example.composeusersapp.data.models.UsersResponse

class UserDataSource {

    val usersApi = RetrofitInstance.api //todo inject

    companion object {
        private const val GENERIC_ERROR_MESSAGE = "Something went wrong"
    }

    suspend fun getUsers(results: Int, page: Int, seed: String): ServiceResult<UsersResponse, GenericError> {
        return try {
            val response = usersApi.getUsers(results, page, seed)
            if (response.results.isEmpty()) {
                ServiceResult(null, GenericError(GENERIC_ERROR_MESSAGE))
            } else {
                ServiceResult(response, null)
            }
        } catch (e: Exception) {
            ServiceResult(null, GenericError(e.message ?: GENERIC_ERROR_MESSAGE))
        }
    }
}