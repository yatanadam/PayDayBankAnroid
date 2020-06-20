package com.payday.kdogruer.core.navigator

import androidx.appcompat.app.AppCompatActivity

/**
 * Created by kaandogruer
 *
 */

open class BaseActivityNavigationController(appCompatActivity: AppCompatActivity) : BaseNavigationController() {

    init {
        this.fragmentManager = appCompatActivity.supportFragmentManager
    }
}