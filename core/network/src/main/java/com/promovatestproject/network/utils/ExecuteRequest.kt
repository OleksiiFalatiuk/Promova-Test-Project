package com.promovatestproject.network.utils

import com.example.common.response.ErrorCodes
import com.example.common.response.ErrorData
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import okhttp3.Call
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import com.example.common.response.Response as CommonResponse

suspend fun <T : Any> executeRequest(request: suspend () -> Response<T>): CommonResponse<T> = try {
    val response = request.invoke()
    when (response.isSuccessful) {
        true -> CommonResponse.Success(response.body() as T)
        else -> createErrorResponse(response)
    }
} catch (e: Exception) {
    if (e is CancellationException) {
        throw e
    }

    createErrorFromException(e)
}

suspend fun executeOkhttpRequest(request: suspend () -> Call): CommonResponse<Unit> = try {
    val call = request.invoke()
    val response = call.execute()

    if (response.isSuccessful) {
        CommonResponse.Success(Unit)
    } else {
        val errorData = ErrorData(null, response.message, emptyList())
        CommonResponse.Error(errorData)
    }
} catch (e: Exception) {
    if (e is CancellationException) {
        throw e
    }

    createErrorFromException(e)
}

private fun mapJsonToErrorString(json: JsonObject): String {
    val meta = "meta"
    val separator = ": "

    return json.entries
        .filter { it.key != meta }
        .mapNotNull { entry ->
            when (entry.value) {
                is JsonPrimitive -> {
                    if ((entry.value as JsonPrimitive).isString) {
                        entry.key + separator + entry.value.toString()
                    } else {
                        null
                    }
                }

                is JsonArray -> {
                    (entry.value as JsonArray).joinToString("\n") { value -> entry.key + separator + value }
                }

                is JsonObject -> {
                    mapJsonToErrorString((entry.value as JsonObject))
                }
            }
        }
        .joinToString("\n")
        .replace("\"", "")
}

private fun <T : Any> createErrorResponse(response: Response<T>): CommonResponse.Error<T> {
    val errorBody = response.errorBody()
    val errorData = if (response.code() == ErrorCodes.INVALID_PARAMS && errorBody != null) {
        val json: JsonObject = Json.decodeFromString(errorBody.string())
        val errors = mapJsonToErrorString(json)

        val errorEntry = "errors"
        val errorKeys = (json[errorEntry] as? JsonObject)?.keys

        ErrorData(
            code = response.code(),
            message = errors.takeIf { it.isNotBlank() } ?: response.code().toString(),
            errorKeys = errorKeys?.toList() ?: emptyList()
        )
    } else {
        ErrorData(
            code = response.code(),
            message = response.message().takeIf { it.isNotBlank() } ?: response.code().toString()
        )
    }

    return CommonResponse.Error(
        errorData
    )
}

private fun <T : Any> createErrorFromException(e: Exception): CommonResponse.Error<T> = when (e) {
    is SocketTimeoutException, is UnknownHostException, is ConnectException ->
        CommonResponse.Error(
            ErrorData(
                code = ErrorCodes.INTERNET_CONNECTION_ERROR,
                message = e.message
            )
        )

    else -> {
        CommonResponse.Error(
            ErrorData(
                code = ErrorCodes.UNKNOWN_ERROR,
                message = e.message
            )
        )
    }
}