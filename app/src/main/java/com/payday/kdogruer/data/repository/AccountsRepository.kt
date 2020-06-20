package com.payday.kdogruer.data.repository

import androidx.lifecycle.LiveData

import com.payday.kdogruer.core.AppExecutors
import com.payday.kdogruer.data.NetworkBoundResource
import com.payday.kdogruer.data.Resource
import com.payday.kdogruer.data.local.dao.AccountDao
import com.payday.kdogruer.data.local.dao.CustomerDao
import com.payday.kdogruer.data.local.dao.TransactionsDao
import com.payday.kdogruer.data.local.entity.AccountEntity
import com.payday.kdogruer.data.local.entity.CustomerEntity
import com.payday.kdogruer.data.local.entity.TransactionEntity
import com.payday.kdogruer.data.remote.RemoteService
import com.payday.kdogruer.data.remote.model.basemodels.BaseApiResponse
import com.payday.kdogruer.data.remote.model.datamodels.requestmodels.AuthRequestModel
import retrofit2.Call

import javax.inject.Inject

/**
 * Created by kaandogruer.
 */

/**
 * Talking to local datasource and remote datasource(web service)
 */

class AccountsRepository @Inject
internal constructor(private val appExecutors: AppExecutors,
                     val accountDao: AccountDao,
                     private val remoteService: RemoteService) {


    //get user accounts online&offline
    fun loadAccounts(customerId: String, isConnect:Boolean): LiveData<Resource<List<AccountEntity>>> {
        return object : NetworkBoundResource<List<AccountEntity>, List<AccountEntity>>(appExecutors,isConnect) {

            override fun saveCallResult(item: List<AccountEntity>) {
                accountDao.insertAll(item)
            }

            override fun shouldFetch(data: List<AccountEntity>?): Boolean {
                return isConnect
            }

            override fun loadFromDb(): LiveData<List<AccountEntity>> {
                return accountDao.accountList
            }

            override fun createCall(): LiveData<BaseApiResponse<List<AccountEntity>>> {
                return remoteService.requestAccounts(customerId)
            }

        }.asLiveData()
    }
}
