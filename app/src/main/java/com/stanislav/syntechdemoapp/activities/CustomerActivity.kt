package com.stanislav.syntechdemoapp.activities

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.stanislav.syntechdemoapp.R
import com.stanislav.syntechdemoapp.adapters.TransactionListAdapter
import com.stanislav.syntechdemoapp.databinding.ActivityCustomerBinding
import com.stanislav.syntechdemoapp.models.Transaction
import com.stanislav.syntechdemoapp.viewmodels.customer.TransactionListViewModel
import com.stanislav.syntechdemoapp.viewmodels.customer.TransactionListViewModelFactory
import kotlinx.android.synthetic.main.activity_customer.*

class CustomerActivity : AppCompatActivity() {


    private var errorSnackbar: Snackbar? = null

    private lateinit var binding: ActivityCustomerBinding
    private lateinit var viewModel: TransactionListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_customer)
        binding.transactionList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        val customerId = intent.getLongExtra("customerId", 0)
        println("customer id : $customerId")
        val factory = TransactionListViewModelFactory(customerId)
        viewModel = factory.create(TransactionListViewModel::class.java)

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel

        transactionList.adapter = TransactionListAdapter(this, viewModel.transactionLiveData) { model: Transaction ->
            this.startActivity(
                    Intent(this, TransactionActivity::class.java)
                            .putExtra("transaction", model)
            )
        }
        transactionList.addItemDecoration(DividerItemDecoration(applicationContext, 1))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}
