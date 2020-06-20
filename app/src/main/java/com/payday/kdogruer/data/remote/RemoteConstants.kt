/**
 * Created by kaandogruer.
 */
package com.payday.kdogruer.data.remote

class RemoteConstants {

    companion object {

        //endpoint
        //const val mBase = "http://localhost:3000/"
        const val mBase = "http://10.0.2.2:3000/"
        //apis
        const val mCustomers = "customers"
        const val mAccounts = "accounts"
        const val mTransactions = "transactions"
        const val mTransactionsById = "transactions/{account_id}"

    }

}


