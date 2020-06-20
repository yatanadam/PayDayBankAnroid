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
import com.payday.kdogruer.data.repository.AccountsRepository
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
class AccountsViewModel @Inject
constructor(var accountsRepository: AccountsRepository) : BaseViewModel() {

    internal var accountsRequestLiveData : MutableLiveData<String>

    val accountsResponseLiveData: LiveData<Resource<List<AccountEntity>>>

    init {
        accountsRequestLiveData = MutableLiveData()
        this.accountsResponseLiveData = Transformations.switchMap(accountsRequestLiveData) {
            when (it) {
                null -> AbsentLiveData.create()
                else -> accountsRepository.loadAccounts(it, isConnect.value!!)
            }
        }
    }

    fun setAccountsRequest(requestModel: String) {
        if (Objects.equals(accountsRequestLiveData.value, requestModel)) {
            return
        }
        accountsRequestLiveData.value = requestModel
    }


}
