/**
 * Created by kaandogruer.
 */
package com.payday.kdogruer.data.remote


import androidx.lifecycle.LiveData
import com.payday.kdogruer.data.remote.model.basemodels.BaseApiResponse
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {

        if (CallAdapter.Factory.getRawType(returnType) != LiveData::class.java) {
            return null
        }

        val observableType = CallAdapter.Factory.getParameterUpperBound(0, returnType as ParameterizedType)

        val rawObservableType = CallAdapter.Factory.getRawType(observableType)

        if (rawObservableType != BaseApiResponse::class.java) {
            throw IllegalArgumentException("type must be a resource")
        }

        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }

        val bodyType = CallAdapter.Factory.getParameterUpperBound(0, observableType)
        return LiveDataCallAdapter<Any>(bodyType)
    }
}