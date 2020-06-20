package com.payday.kdogruer.data.local.converter

import androidx.room.TypeConverter


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.payday.kdogruer.data.local.entity.TransactionEntity

/**
 * this will be used if transaction data listed in account object. Because room doesnt support store object as list.
 * kaandogruer
 */
class TransactionEntityConverter {

    @TypeConverter
    fun fromTransactionEntitiesList(transactionEntities: List<TransactionEntity>?): String? {
        if (transactionEntities == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<TransactionEntity>>() {

        }.type
        return gson.toJson(transactionEntities, type)
    }

    @TypeConverter
    fun toTransactionEntitiesList(transactionEntities: String?): List<TransactionEntity>? {
        if (transactionEntities == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<TransactionEntity>>() {

        }.type
        return gson.fromJson<List<TransactionEntity>>(transactionEntities, type)
    }
}