package com.stanislav.syntechdemoapp.adapters

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.stanislav.syntechdemoapp.R
import com.stanislav.syntechdemoapp.databinding.ItemCustomerBinding
import com.stanislav.syntechdemoapp.models.Customer
import com.stanislav.syntechdemoapp.viewmodels.mainactivity.CustomerViewModel

class CustomerListAdapter(
        private val lifecycleOwner: LifecycleOwner,
        val data: MutableLiveData<Map<Long, Customer>>,
        val onClick: (Customer) -> Unit
) : RecyclerView.Adapter<CustomerListAdapter.ViewHolder>() {

    private val customerList by lazy { generateList() }

    private fun generateList(): MutableList<Customer> = data.value?.values?.toMutableList() ?: mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerListAdapter.ViewHolder {
        val binding: ItemCustomerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_customer, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerListAdapter.ViewHolder, position: Int) {
        holder.bind(customerList[position])
        holder.itemView.setOnClickListener { onClick.invoke(customerList[position]) }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        data.observe(lifecycleOwner, Observer {
            customerList.clear()
            customerList.addAll(generateList())
            this.notifyDataSetChanged()
        })
    }

    override fun getItemCount(): Int = customerList.size

    class ViewHolder(private val binding: ItemCustomerBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = CustomerViewModel()

        fun bind(customer: Customer) {
            viewModel.bind(customer)
            binding.viewModel = viewModel
        }
    }
}