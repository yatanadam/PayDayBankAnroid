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
package com.payday.kdogruer.data

import androidx.lifecycle.LiveData
import androidx.annotation.MainThread
import android.webkit.CookieManager
import androidx.annotation.WorkerThread
import androidx.lifecycle.MediatorLiveData

import com.payday.kdogruer.core.AppExecutors
import com.payday.kdogruer.PayDayApp
import com.payday.kdogruer.R
import com.payday.kdogruer.data.remote.model.basemodels.BaseApiResponse
import com.payday.kdogruer.utils.SharedPrefManager

/**
 * Created by kaandogruer.
 */

abstract class NetworkBoundResource<ResultType, RequestType> @MainThread
protected constructor(private val appExecutors: AppExecutors,private val isConnect:Boolean)  {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {

        val dbSource = loadFromDb()

        result.addSource(dbSource) { resultType ->
            result.removeSource(dbSource)
            if (shouldFetch(resultType)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { rT ->
                    if(isConnect)
                        result.value = Resource.success(rT)
                    else
                        result.value = Resource.error(500,PayDayApp.get()!!.getString(R.string.msg_null_response), rT)
                }
            }
        }

    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) {
            resultType ->
            result.value = Resource.loading(resultType)
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            if (response!!.isSuccess || response.code == 200) {
                appExecutors.diskIO().execute {
                    saveCallResult(processResponse(response)!!)
                    appExecutors.mainThread().execute {
                        result.addSource(loadFromDb()
                        ) { newData -> result.value = (Resource.success(newData)) }
                    }
                }
            } else {
                onFetchFailed()
                result.addSource(dbSource) {
                    newData ->

                }
            }
        }
    }

    protected open fun onFetchFailed() {

    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }


    @WorkerThread
    private fun processResponse(response: BaseApiResponse<RequestType>): RequestType? {
        return response.data
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<BaseApiResponse<RequestType>>

}
