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


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
class Resource<T>(val status: Status, val data: T?, val message: String?,val errorCode: Int?) {

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }

        val resource = o as Resource<*>?

        if (status != resource!!.status) {
            return false
        }
        if (if (message != null) message != resource.message else resource.message != null) {
            return false
        }
        return if (data != null) data == resource.data else resource.data == null
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (message?.hashCode() ?: 0)
        result = 31 * result + (data?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\''.toString() +
                ", data=" + data +
                '}'.toString()
    }

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null,null)
        }

        fun <T> error(code: Int,msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg,code)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null,null)
        }
    }
}
