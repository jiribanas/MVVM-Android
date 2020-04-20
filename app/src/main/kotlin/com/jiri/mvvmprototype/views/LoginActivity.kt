package com.jiri.mvvmprototype.views

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.jiri.mvvmprototype.databinding.LoginActivityBinding
import com.jiri.mvvmprototype.viewmodels.LoginViewModel
import com.jiri.mvvmprototype.R
import com.jiri.mvvmprototype.plumbing.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel


class LoginActivity : BaseActivity()
{
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.setViewModel(viewModel)
        super.onCreate(savedInstanceState)

        val binding: LoginActivityBinding = DataBindingUtil.setContentView(this, R.layout.login_activity)
        binding.viewModel = viewModel

        viewModel.setDefaults()
    }
}