package com.payday.kdogruer.viewmodel

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.payday.kdogruer.PayDayApp
import com.payday.kdogruer.data.Status
import com.payday.kdogruer.data.local.AppDatabase
import com.payday.kdogruer.utils.ConnectivityReceiver
import com.payday.kdogruer.utils.Objects
import com.payday.kdogruer.utils.default
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by kaandogruer.
 */
/**
 * Connection status checking with broadcast. (isConnect).
 */
open class BaseViewModel : ViewModel(), ConnectivityReceiver.ConnectivityReceiverListener {

    internal open var status = MutableLiveData<Status>()
    internal var isConnect = MutableLiveData<Boolean>().default(true)
    private var networkReceiver: ConnectivityReceiver = ConnectivityReceiver(this)

    @Inject
    lateinit var appDatabase: AppDatabase

    init {
        PayDayApp.get()!!.registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    @VisibleForTesting
    fun setLoading(mStatus: Status) {
        if (Objects.equals(status, mStatus)) {
            return
        }
        this.status.value = mStatus
    }

    override fun onNetworkConnectChanged(isConnected: Boolean) {
        if (Objects.equals(isConnect.value, isConnected)) {
            return
        }
        isConnect.value = isConnected
    }

    fun clearLocalDB() {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.clearAllTables()
        }
    }

}
