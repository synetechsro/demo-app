package com.stanislav.syntechdemoapp.viewmodels.customer

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class TransactionListViewModelFactory(
        private val competitionId: Long) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TransactionListViewModel(competitionId) as T
    }
}