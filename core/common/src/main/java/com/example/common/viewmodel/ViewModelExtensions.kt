package com.example.common.viewmodel

import com.example.common.response.ErrorData
import com.example.common.response.Response

suspend fun <T : Any> performUseCase(
    useCase: suspend () -> Response<T>,
    success: suspend (data: T) -> Unit,
    error: suspend (error: ErrorData) -> Unit
) {
    when (val response = useCase()) {
        is Response.Success -> {
            success(response.data)
        }

        is Response.Error -> {
            error(response.error)
        }
    }
}