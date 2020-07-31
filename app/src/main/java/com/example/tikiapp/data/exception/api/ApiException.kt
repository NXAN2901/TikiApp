package com.example.tikiapp.data.exception.api

/**
 * Parser for api error
 *
 * @property E result (maybe Object or List or Array...)
 */
interface ApiException<E> {

    /**
     * Parse error to Error
     *
     *
     */
    fun <T> parseError(errors: T?, result: T? = null): E
}