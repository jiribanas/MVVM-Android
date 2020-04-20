package com.jiri.mvvmprototype.services.api

import com.jiri.mvvmprototype.model.UserDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi
{
    @GET("/auth")
    suspend fun getUserByUsernameAndPassword(@Query("username") username: String, @Query("password") password: String): UserDto
}