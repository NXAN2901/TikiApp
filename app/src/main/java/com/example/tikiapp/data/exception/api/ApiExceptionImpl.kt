package com.example.tikiapp.data.exception.api

import com.example.tikiapp.data.exception.AppError

/**
 * Implementation for parsing Api Error
 */
class ApiExceptionImpl<E> : ApiException<E> {

    @Suppress("UNCHECKED_CAST")
    override fun <T> parseError(errors: T?, result: T?): E {
        // TODO parse Error from API here
        return AppError() as E
    }
}