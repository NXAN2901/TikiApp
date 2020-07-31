package com.example.tikiapp.data.exception

interface InterceptedException<E> {

    fun <T> intercepted(exceptionType: ExceptionType, error: T? = null) : E
}