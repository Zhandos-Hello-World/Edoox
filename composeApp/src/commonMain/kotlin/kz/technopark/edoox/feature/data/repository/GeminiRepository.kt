package kz.technopark.edoox.feature.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kz.technopark.edoox.feature.data.remote.gemini.model.Content
import kz.technopark.edoox.feature.data.remote.gemini.model.GeminiRequest
import kz.technopark.edoox.feature.data.remote.gemini.model.GeminiResponse
import kz.technopark.edoox.feature.data.remote.gemini.model.Part
import kz.technopark.edoox.feature.domain.model.QuizModel
import kz.technopark.edoox.shared.core.log

class GeminiRepository(
    private val client: HttpClient,
    private val apiKey: String,
) {
    companion object {
        private const val BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models"
        private const val MODEL_ID = "gemini-flash-latest"
        private const val ENDPOINT = "$BASE_URL/$MODEL_ID:generateContent"
    }

//    suspend fun askQuestions(): Result<QuizModel> = runCatching {
//        val response: GeminiResponse = client.post(ENDPOINT) {
//            parameter("key", apiKey)
//            contentType(ContentType.Application.Json)
//            setBody(
//                GeminiRequest(
//                    contents = listOf(
//                        Content(
//                            parts = listOf(
//                                Part(prompt)
//                            )
//                        )
//                    ),
//                ),
//            )
//        }.body()
//
//        response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
//            ?: throw Exception("Gemini returned an empty response")
//    }
//
    suspend fun ask(
        prompt: String,

    ): Result<String> = runCatching {
        val response = client.post(
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent"
        ) {
            contentType(ContentType.Application.Json)
            url {
                parameters.append("key", apiKey)
            }
            setBody(
                GeminiRequest(
                    contents = listOf(
                        Content(
                            parts = listOf(
                                Part(prompt)
                            )
                        )
                    ),
                ),
            )
        }


        log("RESPONSE_STATE", response.status.value.toString())

    val raw = response.bodyAsText()
    log("RESPONSE_raw", raw)


    val responseBody = response.body<GeminiResponse>()

        responseBody.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
            ?: throw Exception("Gemini returned an empty response")
    }
}