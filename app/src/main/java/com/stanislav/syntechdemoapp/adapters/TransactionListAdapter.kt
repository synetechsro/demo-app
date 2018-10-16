package com.stanislav.syntechdemoapp.adapters

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.stanislav.syntechdemoapp.R
import com.stanislav.syntechdemoapp.databinding.ItemTransactionBinding
import com.stanislav.syntechdemoapp.models.Transaction
import com.stanislav.syntechdemoapp.viewmodels.customer.TransactionViewModel


class TransactionListAdapter(
        private val lifecycleOwner: LifecycleOwner,
        val data: MutableLiveData<Map<Long, Transaction>>,
        val onClick: (Transaction) -> Unit
) : RecyclerView.Adapter<TransactionListAdapter.ViewHolder>() {

    private val transactionList by lazy { generateList() }

    private fun generateList(): MutableList<Transaction> = data.value?.values?.toMutableList() ?: mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionListAdapter.ViewHolder {
        val binding: ItemTransactionBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_transaction, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionListAdapter.ViewHolder, position: Int) {
        holder.bind(transactionList[position])
        holder.itemView.setOnClickListener { onClick.invoke(transactionList[position]) }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        data.observe(lifecycleOwner, Observer {
            transactionList.clear()
            transactionList.addAll(generateList())
            this.notifyDataSetChanged()
        })
    }

    override fun getItemCount(): Int = transactionList.size

    class ViewHolder(private val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = TransactionViewModel()

        fun bind(transaction: Transaction) {
            viewModel.bind(transaction)
            binding.viewModel = viewModel
        }
    }
}