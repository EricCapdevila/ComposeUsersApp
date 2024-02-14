package com.example.composeusersapp.data.models

class ServiceResult<T, Y>(private val body: T?, private val bodyError: Y?){

    fun getSuccess(): T? = body
    fun getError(): Y? = bodyError

}
