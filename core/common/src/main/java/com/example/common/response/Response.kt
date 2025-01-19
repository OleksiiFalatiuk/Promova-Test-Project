package com.example.common.response

import kotlinx.coroutines.CancellationException

sealed class Response<T : Any> {
    data class Success<T : Any>(var data: T) : Response<T>()
    data class Error<T : Any>(val error: ErrorData) : Response<T>()
}

data class ErrorData(val code: Int?, val message: String?, val errorKeys: List<String> = emptyList())

inline fun <T : Any, R : Any> Response<T>.map(convert: (T) -> R): Response<R> = try {
    when (this) {
        is Response.Error -> Response.Error(error)
        is Response.Success -> Response.Success(convert(data))
    }
} catch (e: Exception) {
    if (e is CancellationException) {
        throw e
    }

    Response.Error(
        ErrorData(
            code = ErrorCodes.UNKNOWN_ERROR,
            message = e.message
        )
    )
}

object ErrorCodes {
    const val INVALID_PARAMS = 422
    const val UNKNOWN_ERROR = 1024
    const val GOOGLE_SIGN_IN_ERROR = 1001
    const val GOOGLE_SIGN_IN_CANCELED = 1002
    const val INTERNET_CONNECTION_ERROR = 1025
}