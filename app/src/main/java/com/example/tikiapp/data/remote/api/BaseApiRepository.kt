package com.example.tikiapp.data.remote.api

import android.util.Log
import com.example.tikiapp.data.exception.AppError
import com.example.tikiapp.data.exception.AppException
import com.example.tikiapp.data.exception.ExceptionType
import com.example.tikiapp.data.exception.InterceptedException
import com.example.tikiapp.data.remote.api.response.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class BaseApiRepository() {

    protected open fun <T> handleApiResult(response: Response<T>, cb: APICallback<T>) {
        if (response.isSuccessful) {
            response.body()?.let {body ->
                if (body !is BaseResponse<*>) {
                    cb.onError(appException<AppError>().intercepted(ExceptionType.UNEXPECTED, null))
                    return
                }
                // TODO check api code if any
                cb.onResult(body)
            }
        }
        // TODO handle http error from retrofit errorBody here (ex: case 401)
    }

    protected fun <E> appException(): InterceptedException<E> = AppException()

    protected fun <T> generateCallback(apiCallback: APICallback<T>) = object: Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            Log.e("ANNX", "onFailure error ${call.request().url} && $t")
            apiCallback.onError(appException<AppError>().intercepted(ExceptionType.UNEXPECTED, null))
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            handleApiResult(response, apiCallback)
        }

    }
}