package com.stanislav.syntechdemoapp.viewmodels.mainactivity

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.stanislav.syntechdemoapp.R
import com.stanislav.syntechdemoapp.models.Customer
import com.stanislav.syntechdemoapp.services.BankService
import com.stanislav.syntechdemoapp.viewmodels.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CustomerListViewModel: BaseViewModel() {
    @Inject
    lateinit var bankService: BankService

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val customerLiveData = MutableLiveData<Map<Long, Customer>>()

    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadCustomers() }

    private lateinit var subscription: Disposable

    init{
        loadCustomers()
    }

    private fun loadCustomers(){
        subscription = bankService.listCustomers()
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

    private fun onRetrieveCustomersListSuccess(result: List<Customer>){
        customerLiveData.postValue(
                result
                    .map {it.customerId to it}
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