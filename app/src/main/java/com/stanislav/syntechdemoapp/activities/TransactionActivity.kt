package com.stanislav.syntechdemoapp.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.stanislav.syntechdemoapp.R
import com.stanislav.syntechdemoapp.models.Transaction
import com.stanislav.syntechdemoapp.viewholders.TransactionDetailViewHolder
import kotlinx.android.synthetic.main.activity_transaction.*

class TransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        val transaction = intent.getParcelableExtra<Transaction>("transaction")
        val viewHolder = TransactionDetailViewHolder(transactionDetailLayout)
        viewHolder.bindModel(transaction)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}
