package com.jiri.mvvmprototype.plumbing

import androidx.lifecycle.ViewModel
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel : ViewModel(), Observable
{
    val isBusy: MutableLiveData<Boolean> = MutableLiveData()

    protected val showAlert = Delegate<Any, Pair<String, String>>()
    val onShowAlert : Event<Any, Pair<String, String>> get() = showAlert

    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback)
    {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback)
    {
        callbacks.remove(callback)
    }

    fun notifyChange()
    {
        callbacks.notifyCallbacks(this, 0, null)
    }

    fun notifyPropertyChanged(fieldId: Int)
    {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
}