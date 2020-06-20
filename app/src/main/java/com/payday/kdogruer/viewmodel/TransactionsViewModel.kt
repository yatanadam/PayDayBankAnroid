package com.payday.kdogruer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope

import com.payday.kdogruer.data.Resource
import com.payday.kdogruer.data.local.entity.AccountEntity
import com.payday.kdogruer.data.repository.CustomerRepository
import com.payday.kdogruer.data.local.entity.CustomerEntity
import com.payday.kdogruer.data.local.entity.TransactionEntity
import com.payday.kdogruer.data.remote.model.datamodels.requestmodels.AuthRequestModel
import com.payday.kdogruer.data.remote.model.datamodels.requestmodels.DateRangeRequestModel
import com.payday.kdogruer.data.repository.TransactionsRepository
import com.payday.kdogruer.utils.AbsentLiveData
import com.payday.kdogruer.utils.Objects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import java.util.*

import javax.inject.Inject

/**
 * Created by kaandogruer.
 */
class TransactionsViewModel @Inject
constructor(var transactionsRepository: TransactionsRepository) : BaseViewModel() {

    internal var transactionsRequestLiveData : MutableLiveData<String>

    val transactionsResponseLiveData: LiveData<Resource<List<TransactionEntity>>>

    init {
        transactionsRequestLiveData = MutableLiveData()
        this.transactionsResponseLiveData = Transformations.switchMap(transactionsRequestLiveData) {
            when (it) {
                null -> AbsentLiveData.create()
                else -> transactionsRepository.loadTransactions(it, isConnect.value!!)
            }
        }
    }

    fun setTransactionRequest(requestModel: String) {
        if (Objects.equals(transactionsRequestLiveData.value, requestModel)) {
            return
        }
        transactionsRequestLiveData.value = requestModel
    }

    fun getTansactionRange(date1: Date, date2:Date):LiveData<List<TransactionEntity>>? {
        return transactionsRepository.getTansactionRange(date1,date2)
    }

}
