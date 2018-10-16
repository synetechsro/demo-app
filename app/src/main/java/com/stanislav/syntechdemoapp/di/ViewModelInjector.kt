package com.stanislav.syntechdemoapp.di

import com.stanislav.syntechdemoapp.viewmodels.mainactivity.CustomerListViewModel
import com.stanislav.syntechdemoapp.viewmodels.customer.TransactionListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(customerListViewModel: CustomerListViewModel)

    fun inject(transactionListViewModel: TransactionListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}