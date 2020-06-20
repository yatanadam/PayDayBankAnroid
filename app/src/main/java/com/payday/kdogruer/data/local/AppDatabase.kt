package com.payday.kdogruer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.payday.kdogruer.data.local.converter.*
import com.payday.kdogruer.data.local.dao.*
import com.payday.kdogruer.data.local.entity.*

@Database(entities = arrayOf(CustomerEntity::class,
        AccountEntity::class,
        TransactionEntity::class),
        version = 1)
@TypeConverters(
        TransactionEntityConverter::class,
        TimestampConverter::class,
        DateConverter::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionsDao
}
