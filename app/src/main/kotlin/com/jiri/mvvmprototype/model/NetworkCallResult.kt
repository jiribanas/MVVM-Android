package com.jiri.mvvmprototype.model

class NetworkCallResult<T>
{
    var result: T? = null
    var succeeded: Boolean = true
    var exception: Exception? = null
    var errorMessage: String? = null
}