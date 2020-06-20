package com.payday.kdogruer.view.main.accounts

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import androidx.lifecycle.Observer
import com.payday.kdogruer.R
import com.payday.kdogruer.core.BaseListFragment
import com.payday.kdogruer.data.local.entity.AccountEntity
import com.payday.kdogruer.data.local.entity.TransactionEntity
import com.payday.kdogruer.data.local.enum.TransactionCategory
import com.payday.kdogruer.databinding.FragmentAccountListBinding
import com.payday.kdogruer.databinding.FragmentTransactionListBinding
import com.payday.kdogruer.databinding.ItemAccountListBinding
import com.payday.kdogruer.databinding.ItemTransactionListBinding
import com.payday.kdogruer.di.Injectable
import com.payday.kdogruer.view.components.animation.ScaleAnimToShowFrame
import com.payday.kdogruer.view.components.piechart.PieSlice
import com.payday.kdogruer.view.main.MainActivity
import com.payday.kdogruer.viewmodel.AccountsViewModel
import com.payday.kdogruer.viewmodel.TransactionsViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.Period
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by kaandogruer.
 */


class AccountListFragment : BaseListFragment<FragmentAccountListBinding, ItemAccountListBinding, AccountEntity, AccountsViewModel>(), Injectable {
    override val layoutResourceId = R.layout.fragment_account_list
    override val viewModelClass: Class<AccountsViewModel> = AccountsViewModel::class.java
    override val viewHolderLayoutId: Int = R.layout.item_account_list


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).setNavTitle(getString(R.string.accounts))
        binding.rvAccounts.adapter = adapter
        binding.customer = (activity as MainActivity).binding.customer
        getAccountList( binding.customer!!.customerId.toString())
    }

    //get transaction list from server and save db
    fun getAccountList(customerId:String) {
        //showLoading()
        viewModel.setAccountsRequest(customerId)
        viewModel.accountsResponseLiveData
                .observe(this, Observer {
                    binding.resource = it
                    if (it.data != null && it.data.size > 0) {
                        adapter.items = ArrayList(it.data)
                        viewModel.accountsResponseLiveData.removeObservers(this)
                    }
                })
    }
    override fun bindView(binding: ItemAccountListBinding, item: AccountEntity, adapterPosition: Int) {
        binding.account = item
    }

    override fun onAdapterItemClickListener(item: AccountEntity, position: Int, binding: ItemAccountListBinding) {
        (activity as MainActivity).mainNavigator.navigateToTransactions(item.accountId!!)
    }

}
