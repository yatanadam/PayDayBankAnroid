package com.payday.kdogruer.data.repository

import androidx.lifecycle.LiveData

import com.payday.kdogruer.core.AppExecutors
import com.payday.kdogruer.data.NetworkBoundResource
import com.payday.kdogruer.data.Resource
import com.payday.kdogruer.data.local.dao.CustomerDao
import com.payday.kdogruer.data.local.entity.CustomerEntity
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

class CustomerRepository @Inject
internal constructor(private val appExecutors: AppExecutors,
                     val customerDao: CustomerDao,
                     private val remoteService: RemoteService) {

    fun saveCustomer(customer: CustomerEntity) {
        appExecutors.diskIO().execute {
            customerDao.insertCustomer(customer)
        }
    }
    fun getCustomer():LiveData<List<CustomerEntity>>? {
        return customerDao.customerList
    }
    //get user with login credentials online
    fun loadCustomer(requestModel: AuthRequestModel): Call<List<CustomerEntity>> {
        return remoteService.requestCustomer(requestModel.email!!,requestModel.password!!)
    }

    //get user with login credentials online&offline
    fun loadCustomer(requestModel: AuthRequestModel, isConnect:Boolean): LiveData<Resource<List<CustomerEntity>>> {
        return object : NetworkBoundResource<List<CustomerEntity>, List<CustomerEntity>>(appExecutors,isConnect) {


            override fun saveCallResult(item: List<CustomerEntity>) {
                customerDao.insertCustomers(item)
            }

            override fun shouldFetch(data: List<CustomerEntity>?): Boolean {
                return isConnect
            }

            override fun loadFromDb(): LiveData<List<CustomerEntity>> {
                return customerDao.customerList
            }

            override fun createCall(): LiveData<BaseApiResponse<List<CustomerEntity>>> {
                return remoteService.requestCustomers(requestModel.email!!,requestModel.password!!)
            }

        }.asLiveData()
    }
}
