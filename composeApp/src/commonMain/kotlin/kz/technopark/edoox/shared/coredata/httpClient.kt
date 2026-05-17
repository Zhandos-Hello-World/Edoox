package kz.technopark.edoox.shared.coredata

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val httpClient = HttpClient(platformHttpClient()) {

    install(ContentNegotiation) {

        json(
            Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            }
        )
    }

    install(HttpTimeout) {
        requestTimeoutMillis = 90_000
        connectTimeoutMillis = 15_000
        socketTimeoutMillis = 90_000
    }
}