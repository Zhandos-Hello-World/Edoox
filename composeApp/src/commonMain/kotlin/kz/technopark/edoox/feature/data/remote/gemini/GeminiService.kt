package kz.technopark.edoox.feature.data.remote.gemini

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kz.technopark.edoox.feature.data.remote.gemini.model.Content
import kz.technopark.edoox.feature.data.remote.gemini.model.GeminiRequest
import kz.technopark.edoox.feature.data.remote.gemini.model.GeminiResponse
import kz.technopark.edoox.feature.data.remote.gemini.model.Part
import kz.technopark.edoox.shared.core.log
import kz.technopark.edoox.shared.coredata.exceptions.GeminiEmptyException


class GeminiService(
    private val client: HttpClient,
    private val apiKey: String,
) {
    private val jsonParser = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    suspend fun <T> execute(
        query: String,
        strategy: DeserializationStrategy<T>,
    ): T {
        val response: GeminiResponse = client.post(ENDPOINT) {
            parameter(GEMINI_PARAM_KEY, apiKey)
            contentType(ContentType.Application.Json)
            setBody(
                GeminiRequest(
                    contents = listOf(
                        Content(
                            parts = listOf(
                                Part(query)
                            )
                        )
                    ),
                ),
            )
        }.body()

        log("RESPONSE", response.candidates?.map { it.content }?.joinToString().toString())
        val json = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
            ?: throw GeminiEmptyException()
        return parse(
            rawString = json,
            strategy = strategy,
        )
    }

    private fun cleanJson(raw: String): String {
        return raw.trim().removePrefix(
                prefix = JSON_PREFIX_START,
            ).removePrefix(
                prefix = JSON_PREFIX_END,
            ).removeSuffix(
                suffix = JSON_SUFFIX,
            ).trim()
    }

    fun <T> parse(rawString: String, strategy: DeserializationStrategy<T>): T {
        val cleanStr = cleanJson(rawString)

        val jsonElement: JsonElement = jsonParser.parseToJsonElement(cleanStr)

        val result: T = jsonParser.decodeFromJsonElement(
            deserializer = strategy,
            element = jsonElement,
        )
        return result
    }

    companion object {
        private const val JSON_PREFIX_START = "```json"
        private const val JSON_PREFIX_END = "```"
        private const val JSON_SUFFIX = "```"
        private const val GEMINI_PARAM_KEY = "key"
        private const val BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models"
        private const val MODEL_ID = "gemini-2.5-flash-lite"
        private const val ENDPOINT = "$BASE_URL/$MODEL_ID:generateContent"
    }
}