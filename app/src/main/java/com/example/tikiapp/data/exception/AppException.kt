package com.example.tikiapp.data.exception

import com.example.tikiapp.data.exception.api.ApiException
import com.example.tikiapp.data.exception.api.ApiExceptionImpl

class AppException<E> : InterceptedException<E> {
    private val apiException: ApiException<E> = ApiExceptionImpl()

    @Suppress("UNCHECKED_CAST")
    override fun <T> intercepted(exceptionType: ExceptionType, error: T?): E {
        return when (exceptionType) {
            ExceptionType.API -> apiException.parseError(error)
            ExceptionType.HTTP -> {
                // TODO Parse error from Retrofit error later
                return AppError(
                    -1
                    , "Unexpected"
                ) as E
            }
            ExceptionType.UNEXPECTED -> AppError(
                -1
                , "Unexpected"
            ) as E
        }
    }


}