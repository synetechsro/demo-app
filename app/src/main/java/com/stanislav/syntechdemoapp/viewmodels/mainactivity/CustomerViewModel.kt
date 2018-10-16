package com.stanislav.syntechdemoapp.viewmodels.mainactivity

import android.arch.lifecycle.MutableLiveData
import com.stanislav.syntechdemoapp.models.Customer
import com.stanislav.syntechdemoapp.viewmodels.BaseViewModel

class CustomerViewModel: BaseViewModel() {
    private val customerName = MutableLiveData<String>()
    private val customerId = MutableLiveData<String>()

    fun bind(customer: Customer){
        customerName.value = customer.customerName
        customerId.value = customer.customerId.toString()
    }

    fun getCustomerName():MutableLiveData<String>{
        return customerName
    }

    fun getCustomerId():MutableLiveData<String>{
        return customerId
    }
}