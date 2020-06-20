package com.payday.kdogruer.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.payday.kdogruer.core.BaseInjectorActivity

import com.payday.kdogruer.utils.SharedPrefManager
import com.payday.kdogruer.view.login.LoginActivity


class SplashActivity :  BaseInjectorActivity() {

    lateinit var sharedPrefManager: SharedPrefManager

    private val SPLASH_DISPLAY_LENGTH = 1000
    var isPermission: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefManager = SharedPrefManager()

        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }


}
