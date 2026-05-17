package kz.technopark.edoox.shared.coredata

import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException

fun Throwable.toErrorType(): ErrorType {
    val message = this.message?.lowercase().orEmpty()

    return when (this) {
        is ConnectTimeoutException,
        is SocketTimeoutException,
            -> ErrorType.NO_INTERNET

        is ClientRequestException -> {
            val statusCode = this.response.status.value
            if (statusCode == 429) ErrorType.GEMINI_LIMIT_EXCEEDED else ErrorType.SERVER_ERROR
        }

        is ServerResponseException -> ErrorType.SERVER_ERROR

        else -> {
            when {
                message.contains("offline") || message.contains("connection failed") || message.contains(
                    "host"
                ) -> ErrorType.NO_INTERNET

                message.contains("quota") || message.contains("429") || message.contains("limit exceeded") -> ErrorType.GEMINI_LIMIT_EXCEEDED

                else -> ErrorType.UNKNOWN
            }
        }
    }
}