package com.stanislav.syntechdemoapp.viewmodels.customer

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.stanislav.syntechdemoapp.R
import com.stanislav.syntechdemoapp.models.CustomerDetail
import com.stanislav.syntechdemoapp.models.Transaction
import com.stanislav.syntechdemoapp.services.BankService
import com.stanislav.syntechdemoapp.viewmodels.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TransactionListViewModel(val customerId: Long): BaseViewModel() {
    @Inject
    lateinit var bankService: BankService

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val transactionLiveData = MutableLiveData<Map<Long, Transaction>>()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadCustomers() }

    private lateinit var subscription: Disposable

    init{
        loadCustomers()
    }

    private fun loadCustomers(){
        subscription = bankService.getTransactionsByCustomerId(customerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveCustomersListStart() }
                .doOnTerminate { onRetrieveCustomersListFinish() }
                .subscribe(
                        { result -> onRetrieveCustomersListSuccess(result) },
                        { error -> onRetrieveCustomersListError(error) }
                )
    }

    private fun onRetrieveCustomersListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveCustomersListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCustomersListSuccess(result: CustomerDetail){
        transactionLiveData.postValue(result.transactions
                .map {it.id to it}
                .toMap()
        )
    }

    private fun onRetrieveCustomersListError(error: Throwable) {
        error.printStackTrace()
        errorMessage.value = R.string.error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}