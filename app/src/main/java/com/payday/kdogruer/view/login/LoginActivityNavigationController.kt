package com.payday.kdogruer.view.login

import com.payday.kdogruer.R
import com.payday.kdogruer.core.BaseFragment
import com.payday.kdogruer.core.navigator.BaseActivityNavigationController
import javax.inject.Inject

class LoginActivityNavigationController @Inject constructor(loginActivity: LoginActivity) : BaseActivityNavigationController(loginActivity) {

    var activeFragment: BaseFragment? = null

    init {
        this.containerId = R.id.flContainer

    }

    fun navigateToLogin() {
        clearHistory()
        activeFragment = BaseFragment.newInstance(LoginFragment())
        replace(activeFragment!!)
    }

}