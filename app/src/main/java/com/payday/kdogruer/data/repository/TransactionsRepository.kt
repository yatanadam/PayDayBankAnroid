package com.payday.kdogruer.data.repository

import androidx.lifecycle.LiveData

import com.payday.kdogruer.core.AppExecutors
import com.payday.kdogruer.data.NetworkBoundResource
import com.payday.kdogruer.data.Resource
import com.payday.kdogruer.data.local.dao.CustomerDao
import com.payday.kdogruer.data.local.dao.TransactionsDao
import com.payday.kdogruer.data.local.entity.AccountEntity
import com.payday.kdogruer.data.local.entity.CustomerEntity
import com.payday.kdogruer.data.local.entity.TransactionEntity
import com.payday.kdogruer.data.remote.RemoteService
import com.payday.kdogruer.data.remote.model.basemodels.BaseApiResponse
import com.payday.kdogruer.data.remote.model.datamodels.requestmodels.AuthRequestModel
import retrofit2.Call
import java.util.*

import javax.inject.Inject

/**
 * Created by kaandogruer.
 */

/**
 * Talking to local datasource and remote datasource(web service)
 */

class TransactionsRepository @Inject
internal constructor(private val appExecutors: AppExecutors,
                     val transactionsDao: TransactionsDao,
                     private val remoteService: RemoteService) {

    fun getTansactionRange(date1:Date,date2: Date):LiveData<List<TransactionEntity>>? {
        return transactionsDao.getTransactionSort(date1,date2)
    }
    //get user transactions online&offline
    fun loadTransactions(accountId: String, isConnect:Boolean): LiveData<Resource<List<TransactionEntity>>> {
        return object : NetworkBoundResource<List<TransactionEntity>, List<TransactionEntity>>(appExecutors,isConnect) {

            override fun saveCallResult(item: List<TransactionEntity>) {
                transactionsDao.insertAll(item)
            }

            override fun shouldFetch(data: List<TransactionEntity>?): Boolean {
                return isConnect
            }

            override fun loadFromDb(): LiveData<List<TransactionEntity>> {
                return transactionsDao.transactionList
            }

            override fun createCall(): LiveData<BaseApiResponse<List<TransactionEntity>>> {
                return remoteService.requestTransactions(accountId)
            }

        }.asLiveData()
    }
}
