package com.stanislav.syntechdemoapp.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.stanislav.syntechdemoapp.R
import com.stanislav.syntechdemoapp.adapters.CustomerListAdapter
import com.stanislav.syntechdemoapp.databinding.ActivityMainBinding
import com.stanislav.syntechdemoapp.models.Customer
import com.stanislav.syntechdemoapp.viewmodels.mainactivity.CustomerListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var errorSnackbar: Snackbar? = null

    private lateinit var listViewModel: CustomerListViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.customerList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listViewModel = ViewModelProviders.of(this).get(CustomerListViewModel::class.java)
        listViewModel.errorMessage.observe(this, Observer {
            errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = listViewModel

        customerList.adapter = CustomerListAdapter(this, listViewModel.customerLiveData) {
            model: Customer  ->
            this.startActivity(
                    Intent(this, CustomerActivity::class.java)
                            .putExtra("customerId", model.customerId)
            )
        }
        customerList.addItemDecoration(DividerItemDecoration(applicationContext, 1))
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, listViewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}
