package com.stanislav.syntechdemoapp.viewholders

import android.support.design.widget.Snackbar
import android.view.View
import com.bumptech.glide.Glide
import com.stanislav.syntechdemoapp.R
import com.stanislav.syntechdemoapp.models.Transaction
import com.stanislav.syntechdemoapp.models.TransactionType
import kotlinx.android.synthetic.main.activity_transaction.view.*

class TransactionDetailViewHolder(val view: View) {

    fun bindModel(model: Transaction) {
        var resId = 0
        when (model.type) {
            "bank" -> resId = TransactionType.BANK.drawableId
            "kids" -> resId = TransactionType.KIDS.drawableId
            "gas" -> resId = TransactionType.GAS.drawableId
            "house" -> resId = TransactionType.HOME.drawableId
        }
        Glide.with(view).load(resId).into(view.transactionDetailImage)
        view.transactionDetailDescription.text = view.resources.getString(R.string.transactionDetailDescriptionString, model.description)
        view.transactionDetailAmount.text = view.resources.getString(R.string.transactionDetailAmountString, model.amount)
        view.transactionDetailDate.text = view.resources.getString(R.string.transactionDetailDateString)
        view.fab.setOnClickListener {
            val snackbar = Snackbar.make(view.fab, "fab clicked", 1000)
            snackbar.show()
        }
    }
}