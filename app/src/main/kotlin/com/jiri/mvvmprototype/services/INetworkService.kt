package com.jiri.mvvmprototype.services

import com.jiri.mvvmprototype.model.NetworkCallResult
import com.jiri.mvvmprototype.model.UserDto


interface INetworkService
{
    suspend fun getUserByUsernameAndPassword(username: String, password: String): NetworkCallResult<UserDto>
}