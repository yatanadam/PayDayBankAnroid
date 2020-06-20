package com.payday.kdogruer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.payday.kdogruer.data.NetworkBoundResource

import com.payday.kdogruer.data.Resource
import com.payday.kdogruer.data.repository.CustomerRepository
import com.payday.kdogruer.data.local.entity.CustomerEntity
import com.payday.kdogruer.data.local.entity.TransactionEntity
import com.payday.kdogruer.data.remote.model.basemodels.BaseApiResponse
import com.payday.kdogruer.data.remote.model.datamodels.requestmodels.AuthRequestModel
import com.payday.kdogruer.utils.AbsentLiveData
import com.payday.kdogruer.utils.Objects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

import javax.inject.Inject

/**
 * Created by kaandogruer on 10.4.2018 .
 */
class CustomerViewModel @Inject
constructor(var customerRepository: CustomerRepository) : BaseViewModel() {

    internal var customerRequestLiveData : MutableLiveData<AuthRequestModel>

    val customerResponseLiveData: LiveData<Resource<List<CustomerEntity>>>

    init {
        customerRequestLiveData = MutableLiveData()
        this.customerResponseLiveData = Transformations.switchMap(customerRequestLiveData) {
            when (it) {
                null -> AbsentLiveData.create()
                else -> customerRepository.loadCustomer(it, isConnect.value!!)
            }
        }
    }

    fun setAuthRequestModel(requestModel: AuthRequestModel) {
        if (Objects.equals(customerRequestLiveData.value, requestModel)) {
            return
        }
        customerRequestLiveData.value = requestModel
    }

    fun requestLoginCustomer(requestModel: AuthRequestModel): LiveData<Resource<List<CustomerEntity>>> {
        return customerRepository.loadCustomer(requestModel,isConnect.value!!)
    }

    fun saveCustomer(customerEntity: CustomerEntity){
        customerRepository.saveCustomer(customerEntity)
    }
    fun getCustomer():LiveData<List<CustomerEntity>>? {
        return customerRepository.getCustomer()
    }


}
