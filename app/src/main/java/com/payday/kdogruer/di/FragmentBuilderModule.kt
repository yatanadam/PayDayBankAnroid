package com.payday.kdogruer.di

import com.payday.kdogruer.view.login.LoginFragment
import com.payday.kdogruer.view.main.MainFragment
import com.payday.kdogruer.view.main.accounts.AccountListFragment
import com.payday.kdogruer.view.main.transactions.TransactionListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by kaandogruer.
 */
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun contributeLoginFragment(): LoginFragment
    @ContributesAndroidInjector
    internal abstract fun contributeMainFragment(): MainFragment
    @ContributesAndroidInjector
    internal abstract fun contributeTransactionListFragment(): TransactionListFragment
    @ContributesAndroidInjector
    internal abstract fun contributeAccountListFragment(): AccountListFragment

}
