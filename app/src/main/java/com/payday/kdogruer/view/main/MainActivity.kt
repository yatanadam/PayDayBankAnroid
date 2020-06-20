package com.payday.kdogruer.view.main

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.payday.kdogruer.R
import com.payday.kdogruer.core.BaseInjectableMenuActivity
import com.payday.kdogruer.databinding.ActivityMainBinding
import com.payday.kdogruer.view.base.LeftMenuClicksCallback
import com.payday.kdogruer.view.login.LoginActivityNavigationController
import com.payday.kdogruer.viewmodel.CustomerViewModel
import javax.inject.Inject


class MainActivity : BaseInjectableMenuActivity<CustomerViewModel, ActivityMainBinding>(),LeftMenuClicksCallback {
    override val layoutResourceId = R.layout.activity_main
    override val viewModelClass: Class<CustomerViewModel> = CustomerViewModel::class.java

    @Inject
    lateinit var mainNavigator: MainActivityNavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavMenu(getString(R.string.homepage))
        dataBinding.navMenu.callback = this
        viewModel.getCustomer()!!.observe(this, Observer {
            if(it.size>0) {
                binding.customer = it.get(0)
                mainNavigator.navigateToMain()
            }
        })


    }

    override fun onBackPressed() {
        if(mainNavigator.fragmentManager!!.backStackEntryCount > 1){
            mainNavigator.fragmentManager!!.popBackStack()
            setStateOnBack()
        }else{

        }
    }
    fun setStateOnBack() {
        if(getCurrentFragmentTag() != null && !getCurrentFragmentTag()!!.isEmpty()){

        }
    }

    private fun getCurrentFragmentTag(): String? {
        val fragmentTag = mainNavigator.fragmentManager!!.getBackStackEntryAt(mainNavigator.fragmentManager!!.backStackEntryCount - 1).name
        return fragmentTag
    }

    override fun onMain() {
        dataBinding.drawerLayout.closeDrawers()
        mainNavigator.navigateToMain()
    }

    override fun onAccounts() {
        dataBinding.drawerLayout.closeDrawers()
        mainNavigator.navigateToAccounts()
    }

    override fun onExit() {
        //TODO("Not yet implemented")
    }
}
