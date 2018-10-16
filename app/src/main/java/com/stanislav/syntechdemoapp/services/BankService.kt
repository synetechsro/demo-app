package com.stanislav.syntechdemoapp.services

import com.stanislav.syntechdemoapp.models.Customer
import com.stanislav.syntechdemoapp.models.CustomerDetail
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface BankService {
    @GET("/v1/customers")
    fun listCustomers(): Observable<List<Customer>>
    @GET("/v1/customers/{customerId}")
    fun getTransactionsByCustomerId(@Path(value="customerId") customerId: Long): Observable<CustomerDetail>
}