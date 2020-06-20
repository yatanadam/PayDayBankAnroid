/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.payday.kdogruer.di

import android.app.Application
import androidx.room.Room
import com.payday.kdogruer.PayDayApp

import com.payday.kdogruer.data.local.AppDatabase
import com.payday.kdogruer.data.local.dao.*

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher

@Module(includes = [ViewModelModule::class])
internal class AppModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "payday-db")
                .build()
    }

    @Singleton
    @Provides
    fun provideCustomerDao(db: AppDatabase): CustomerDao {
        return db.customerDao()
    }

    @Singleton
    @Provides
    fun provideAccountDao(db: AppDatabase): AccountDao {
        return db.accountDao()
    }

    @Singleton
    @Provides
    fun provideTransactionsDao(db: AppDatabase): TransactionsDao {
        return db.transactionDao()
    }

    @Singleton
    @Provides
    fun provideDispatcher() : Dispatcher {
        return Dispatcher()
    }

    @Singleton
    @Provides
    fun provideApplication() : PayDayApp{
        return PayDayApp()
    }
}
