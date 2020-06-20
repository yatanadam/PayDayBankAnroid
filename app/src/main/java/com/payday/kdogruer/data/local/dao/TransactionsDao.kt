/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.payday.kdogruer.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*

import com.payday.kdogruer.data.local.entity.TransactionEntity
import java.util.*

@Dao
interface TransactionsDao {
    @get:Query("SELECT * FROM transactions ORDER BY date DESC")
    val transactionList: LiveData<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE date BETWEEN :date1 AND :date2 ORDER BY date DESC")
    fun getTransactionSort(date1: Date, date2:Date): LiveData<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE id=:transactionId")
    fun getTransactionById(transactionId: String): LiveData<TransactionEntity>

    @Query("DELETE FROM transactions")
    fun deleteAll()
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transaction: TransactionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(transaction: List<TransactionEntity>)

    @Update
    fun updateUser(transaction: TransactionEntity)

    @Delete
    fun deleteUser(transaction: TransactionEntity)


}
