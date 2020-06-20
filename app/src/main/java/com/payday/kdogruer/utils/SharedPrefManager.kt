package com.payday.kdogruer.utils

import android.content.SharedPreferences

import com.payday.kdogruer.PayDayApp


class SharedPrefManager {


    internal var pref: SharedPreferences
    internal var editor: SharedPreferences.Editor
    internal var PRIVATE_MODE = 0

    init {
        pref = PayDayApp.get()!!.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }


    companion object {
        private val PREF_NAME = "PAYDAY_PREF"
        private val USER_NAME = "USER_NAME"
    }
}
