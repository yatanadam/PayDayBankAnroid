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

import com.payday.kdogruer.data.local.entity.CustomerEntity

@Dao
interface CustomerDao {
    @get:Query("SELECT * FROM customer")
    val customer: LiveData<CustomerEntity>

    @get:Query("SELECT * FROM customer")
    val customerList: LiveData<List<CustomerEntity>>

    @Query("DELETE FROM customer")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomer(vararg customer: CustomerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomers(customers: List<CustomerEntity>)

    @Update
    fun updateUser(customer: CustomerEntity)

    @Delete
    fun deleteUser(customer: CustomerEntity)


}
