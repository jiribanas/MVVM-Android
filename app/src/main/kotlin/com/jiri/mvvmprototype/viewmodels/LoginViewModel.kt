package com.jiri.mvvmprototype.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jiri.mvvmprototype.model.NetworkCallResult
import com.jiri.mvvmprototype.model.UserDto
import com.jiri.mvvmprototype.plumbing.BaseViewModel
import com.jiri.mvvmprototype.services.INetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(val networkService : INetworkService) : BaseViewModel()
{
    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    fun setDefaults()
    {
        username.value = "test-user"
        password.value = ""
    }

    fun login()
    {
        if (username.value.isNullOrEmpty() || password.value.isNullOrEmpty())
        {
            showAlert(this, Pair("Unable to login", "Please, enter your username and password"))
            return
        }

        viewModelScope.launch(Dispatchers.Main)
        {
            isBusy.value = true

            var loginResult = NetworkCallResult<UserDto>()

            withContext(Dispatchers.Default)
            {
                loginResult = networkService.getUserByUsernameAndPassword(username.value!!, password.value!!)
            }

            isBusy.value = false

            if (loginResult.succeeded)
            {
                showAlert(this, Pair("Login Successful!", "Now you will be taken to another screen"))
            }
            else
            {
                showAlert(this, Pair("Unable to login", loginResult.errorMessage ?: "Unknown error"))
            }
        }
    }
}