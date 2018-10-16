package com.stanislav.syntechdemoapp.models

class CustomerDetail(
        val customerId: Long,
        val customerCode: Long,
        val customerName: String,
        val transactions: List<Transaction>
): Entity()