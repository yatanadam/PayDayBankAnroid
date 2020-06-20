package com.payday.kdogruer.data.local

class RoomConstants {
    companion object {

        //region customer
        const val CUSTOMER_TABLE = "customer"

        const val CUSTOMER_ID = "id"
        const val CUSTOMER_NAME = "first_name"
        const val CUSTOMER_LAST_NAME = "last_name"
        const val CUSTOMER_GENDER = "gender"
        const val CUSTOMER_EMAIL = "email"
        const val CUSTOMER_PASS = "password"
        const val CUSTOMER_DOB = "dob"
        const val CUSTOMER_PHONE = "phone"
        const val CUSTOMER_ACCOUNTS = "accounts"
        const val CUSTOMER_TRANSACTIONS = "transactions"
        //endregion


        //region account
        const val ACCOUNT_TABLE = "account"

        const val ACCOUNT_ID = "id"
        const val ACCOUNT_CUSTOMER_ID = "customer_id"
        const val ACCOUNT_IBAN = "iban"
        const val ACCOUNT_TYPE = "type"
        const val ACCOUNT_DATE_CREATED = "date_created"
        const val ACCOUNT_ACTIVE = "active"
        //endregion

        //region transactions
        const val TRANSACTIONS_TABLE = "transactions"

        const val TRANSACTION_ID = "id"
        const val TRANSACTION_ACCOUNT_ID = "account_id"
        const val TRANSACTION_AMOUNT = "amount"
        const val TRANSACTION_VENDOR = "vendor"
        const val TRANSACTION_CATEGORY = "category"
        const val TRANSACTION_DATE = "date"
        //endregion

    }
}
