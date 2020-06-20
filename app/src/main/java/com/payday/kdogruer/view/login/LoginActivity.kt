package com.payday.kdogruer.view.login

import android.os.Bundle
import com.payday.kdogruer.R
import com.payday.kdogruer.core.BaseInjectableActivity
import com.payday.kdogruer.databinding.ActivityLoginBinding
import com.payday.kdogruer.utils.SharedPrefManager
import com.payday.kdogruer.viewmodel.CustomerViewModel
import javax.inject.Inject


/**
 * Created by kaandogruer.
 */

class LoginActivity : BaseInjectableActivity<CustomerViewModel, ActivityLoginBinding>() {
    override val layoutResourceId: Int = R.layout.activity_login
    override val viewModelClass: Class<CustomerViewModel> = CustomerViewModel::class.java

    @Inject
    lateinit var detailNavigator: LoginActivityNavigationController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailNavigator.navigateToLogin()
    }

}
