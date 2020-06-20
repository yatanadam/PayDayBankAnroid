package com.payday.kdogruer.di

import androidx.lifecycle.ViewModel
import com.payday.kdogruer.viewmodel.*

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CustomerViewModel::class)
    internal abstract fun bindCustomerViewModel(customerViewModel: CustomerViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(TransactionsViewModel::class)
    internal abstract fun bindTransactionViewModel(transactionsViewModel: TransactionsViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(AccountsViewModel::class)
    internal abstract fun bindAccountsViewModel(accountsViewModel: AccountsViewModel): ViewModel


}
