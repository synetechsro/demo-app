package com.stanislav.syntechdemoapp.viewmodels.customer

import android.arch.lifecycle.MutableLiveData
import com.stanislav.syntechdemoapp.models.Transaction
import com.stanislav.syntechdemoapp.models.TransactionType
import com.stanislav.syntechdemoapp.viewmodels.BaseViewModel
import kotlin.math.round

class TransactionViewModel: BaseViewModel() {
    private val transactionDescription = MutableLiveData<String>()
    private val transactionAmount = MutableLiveData<String>()
    private val transactionImage = MutableLiveData<Int>()

    fun bind(transaction: Transaction){
        transactionDescription.value = transaction.description
        transactionAmount.value = round(transaction.amount).toString()
        when(transaction.type) {
            "bank" -> transactionImage.value = TransactionType.BANK.drawableId
            "kids" -> transactionImage.value = TransactionType.KIDS.drawableId
            "gas" -> transactionImage.value = TransactionType.GAS.drawableId
            "house" -> transactionImage.value = TransactionType.HOME.drawableId
        }
    }

    fun getTransactionDescription(): MutableLiveData<String> {
        return transactionDescription
    }

    fun getTransactionAmount(): MutableLiveData<String> {
        return transactionAmount
    }

    fun getTransactionType(): MutableLiveData<Int> {
        return transactionImage
    }
}