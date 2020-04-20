package com.jiri.mvvmprototype

import com.jiri.mvvmprototype.services.INetworkService
import com.jiri.mvvmprototype.services.NetworkService
import com.jiri.mvvmprototype.viewmodels.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<INetworkService> { NetworkService() }

    viewModel { LoginViewModel(get()) }
}