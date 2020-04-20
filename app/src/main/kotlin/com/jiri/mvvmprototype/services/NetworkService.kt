package com.jiri.mvvmprototype.services

import com.jiri.mvvmprototype.Constants
import com.jiri.mvvmprototype.model.NetworkCallResult
import com.jiri.mvvmprototype.model.UserDto
import com.jiri.mvvmprototype.services.api.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class NetworkService() : INetworkService
{
    private fun userApi() : UserApi {

        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        return retrofit.create(UserApi::class.java)
    }

    override suspend fun getUserByUsernameAndPassword(username: String, password: String): NetworkCallResult<UserDto>
    {
        var response = NetworkCallResult<UserDto>()

        withContext(Dispatchers.Default)
        {
            try
            {
                delay(5000)
                val user = userApi().getUserByUsernameAndPassword(username, password)
                response.result = user
            }
            catch (e: Exception)
            {
                response.succeeded = false
                response.exception = e
                response.errorMessage = e.message
            }
        }

        return response
    }
}