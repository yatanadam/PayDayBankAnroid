package com.payday.kdogruer.view.main

import com.payday.kdogruer.R
import com.payday.kdogruer.core.BaseFragment
import com.payday.kdogruer.core.navigator.BaseActivityNavigationController
import com.payday.kdogruer.view.main.accounts.AccountListFragment
import com.payday.kdogruer.view.main.transactions.TransactionListFragment
import javax.inject.Inject

class MainActivityNavigationController @Inject constructor(mainActivity: MainActivity) : BaseActivityNavigationController(mainActivity) {

    var activeFragment: BaseFragment? = null

    init {
        this.containerId = R.id.flContainer

    }

    fun navigateToMain() {
        clearHistory()
        activeFragment = BaseFragment.newInstance(MainFragment())
        replace(activeFragment!!)
    }

    fun navigateToTransactions(accountId:String) {
        clearHistory()
        activeFragment = BaseFragment.newInstance(TransactionListFragment(),accountId)
        replace(activeFragment!!)
    }
    fun navigateToAccounts() {
        clearHistory()
        activeFragment = BaseFragment.newInstance(AccountListFragment())
        replace(activeFragment!!)
    }

}