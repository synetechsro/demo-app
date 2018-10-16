package com.stanislav.syntechdemoapp.viewmodels

import android.arch.lifecycle.ViewModel
import com.stanislav.syntechdemoapp.di.DaggerViewModelInjector
import com.stanislav.syntechdemoapp.di.NetworkModule
import com.stanislav.syntechdemoapp.di.ViewModelInjector
import com.stanislav.syntechdemoapp.viewmodels.customer.TransactionListViewModel
import com.stanislav.syntechdemoapp.viewmodels.mainactivity.CustomerListViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is CustomerListViewModel -> injector.inject(this)
            is TransactionListViewModel -> injector.inject(this)
        }
    }
}