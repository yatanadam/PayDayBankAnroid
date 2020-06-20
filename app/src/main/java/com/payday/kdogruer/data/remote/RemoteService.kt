package com.payday.kdogruer.data.remote

/**
 * Created by kaandogruer.
 */

import androidx.lifecycle.LiveData
import com.payday.kdogruer.data.local.entity.*
import com.payday.kdogruer.data.remote.model.basemodels.BaseApiResponse
import retrofit2.Call
import retrofit2.http.*

interface RemoteService {

    /** Customers */
    @GET(RemoteConstants.mCustomers)
    fun requestCustomer(@Query("email") mail:String,
                         @Query("password") password: String): Call<List<CustomerEntity>>

    /** Customers LiveData*/
    @GET(RemoteConstants.mCustomers)
    fun requestCustomers(@Query("email") mail:String,
                         @Query("password") password: String): LiveData<BaseApiResponse<List<CustomerEntity>>>

    /** Accounts */
    @GET(RemoteConstants.mAccounts)
    fun requestAccounts(@Query("customer_id") customerId:String): LiveData<BaseApiResponse<List<AccountEntity>>>

    /** Transactions */
    @GET(RemoteConstants.mTransactions)
    fun requestTransactions(@Query("account_id") accountId:String): LiveData<BaseApiResponse<List<TransactionEntity>>>

    /** Transactions */
    @GET(RemoteConstants.mTransactions)
    fun requestTransactions(): LiveData<BaseApiResponse<List<TransactionEntity>>>
}


