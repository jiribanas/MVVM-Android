package com.jiri.mvvmprototype.plumbing

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog

open class BaseActivity : AppCompatActivity()
{
    private var progressIndicatorDialog: SweetAlertDialog? = null
    private var viewModel: BaseViewModel? = null

    fun setViewModel(vm: BaseViewModel)
    {
        viewModel = vm

        if (viewModel != null)
        {
            viewModel!!.isBusy.observe(this, Observer
            {
                isBusy ->
                if (isBusy)
                {
                    showProgressIndicator("Please wait...")
                }
                else
                {
                    hideProgressIndicator()
                }
            })

            viewModel!!.onShowAlert.removeAllHandlers()
            viewModel!!.onShowAlert += fun(_, envelope)
            {
                showAlertDialog(envelope.first, envelope.second)
            }
        }
    }

    fun showAlertDialog(title: String, message: String)
    {
        SweetAlertDialog(this)
                .setTitleText(title)
                .setContentText(message)
                .show()
    }

    fun showProgressIndicator(title: String)
    {
        if (progressIndicatorDialog == null)
        {
            progressIndicatorDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        }

        if (progressIndicatorDialog!!.isShowing)
        {
            progressIndicatorDialog!!.hide()
        }

        progressIndicatorDialog!!.titleText = title;
        progressIndicatorDialog!!.setCancelable(false);
        progressIndicatorDialog!!.show();
    }

    fun hideProgressIndicator()
    {
        if (progressIndicatorDialog?.isShowing == true)
        {
            progressIndicatorDialog!!.hide();
        }
    }
}